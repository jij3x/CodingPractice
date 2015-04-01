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
_output = metadata["output"]
for index, param in enumerate(_params):
    _params[index]["variableName"] = "_PARAM_{}_".format(str(index))
if "return" in metadata["interface"]:
    metadata["interface"]["return"]["variableName"] = "_RETURN_"

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


def fetch_param_code(template_fname, param):
    param_code = ""

    with open(template_fname) as mapper_file:
        regex = re.compile(r"_PARAM_")
        saw_seperator = False
        for line in mapper_file:
            if line.strip() == type_map[param["type"]]["seperator"]:
                if saw_seperator:
                    break
                saw_seperator = True
            elif saw_seperator:
                line = regex.sub(param["variableName"], line)
                param_code += line

    return param_code


for index, param in enumerate(_params):
    driver_src += fetch_param_code(mapper_fname, param) + "\n"

inputs = ""
for index, param in enumerate(_params):
    inputs += (", " if index > 0 else "") + _params[index]["variableName"]
return_param = ""
if "return" in metadata["interface"]:
    _return = metadata["interface"]["return"]
    return_param = "{} {} = ".format(type_map[_return["type"]]["returnType"], _return["variableName"])
driver_src += " " * 12 + "{}(new Solution()).{}({});\n".format(return_param, _function, inputs)

inputs = eval("metadata['interface']" + _output["from"] + "['variableName']")
inputs += ', "{}"'.format(_output["overrideSize"]) if "overrideSize" in _output else ""
serializer = type_map[eval("metadata['interface']" + _output["from"] + "['type']")]["serializer"]
driver_src += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

driver_src += driver_tail
print(driver_src)
