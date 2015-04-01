import sys
import json
import re

metadata_fname = sys.argv[1]
mapper_fname = "Mapper.java"
drv_tml_fname = "driver_template.java"

type_map = {
    ":void": {
        "returnType": ""
    },
    ":int": {
        "returnType": "int",
        "serializer": "serializeInt",
        "seperator": "// |:int"
    },
    ":array<:int>": {
        "returnType": "int[]",
        "serializer": "serializeIntArray",
        "seperator": "// |:intArray"
    },
    ":list<:int>": {
        "returnType": "List<Integer>",
        "serializer": "serializeIntList",
        "seperator": "// |:intList"
    }
}

with open(metadata_fname) as metadata_file:
    metadata = json.load(metadata_file)
_function = metadata["interface"]["functionName"]
_params = metadata["interface"]["paramList"]
_return = metadata["interface"]["return"]
_output = metadata["output"]

driver_src = ""
driver_tail = ""
with open(drv_tml_fname) as driver_template:
    is_tail = False
    for line in driver_template:
        if line.strip() == "// inject here":
            is_tail = True
        elif not is_tail:
            driver_src += line
        else:
            driver_tail += line


def fetch_param_code(template_fname, param_type, index):
    param_code = ""

    with open(template_fname) as mapper_file:
        regex = re.compile(r"(_[a-zA-Z0-9_]*)")
        saw_seperator = False
        for line in mapper_file:
            if line.strip() == type_map[param_type]["seperator"]:
                if saw_seperator:
                    break
                saw_seperator = True
            elif saw_seperator:
                line = regex.sub("\g<1>" + str(index) + "_", line)
                param_code += line

    return param_code


for index, param in enumerate(_params):
    driver_src += fetch_param_code(mapper_fname, param["type"], index) + "\n"

inputs = ""
for index, param in enumerate(_params):
    inputs += (", " if index > 0 else "") + "_PARAM_{}".format(str(index))
return_type = type_map[param["type"]]["returnType"]
driver_src += " " * 12 + "{} _RETURN_ = (new Solution()).{}({});\n".format(return_type, _function, inputs)

inputs = _output["from"] + (", {}".format(_output["overrideSize"]) if "overrideSize" in _output else "")
serializer = type_map[eval("metadata['interface']" + _output["from"])["type"]]["serializer"]
driver_src += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

driver_src += driver_tail
print(driver_src)
