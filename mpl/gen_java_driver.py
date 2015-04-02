import sys
import json
import re

metadata_fname = sys.argv[1]
mapper_fname = "Mapper.java"
drv_tml_fname = "driver_template.java"
interface_fname = "Solution.java"

type_map = {
    ":void": {
        "javaType": ""
    },
    ":int": {
        "javaType": "int",
        "serializer": "serializeInt",
        "seperator": "// |:int"
    },
    ":array<:int>": {
        "javaType": "int[]",
        "serializer": "serializeIntArray",
        "seperator": "// |:intArray"
    },
    ":list<:int>": {
        "javaType": "List<Integer>",
        "serializer": "serializeIntList",
        "seperator": "// |:intList"
    }
}

#
# Read problem description, and compose metadata
#
with open(metadata_fname) as metadata_file:
    metadata = json.load(metadata_file)
_function = metadata["interface"]["functionName"]
_params = metadata["interface"]["paramList"]
_output = metadata["output"]
for index, param in enumerate(_params):
    _params[index]["codeName"] = "_PARAM_{}_".format(str(index))
if "return" in metadata["interface"]:
    metadata["interface"]["return"]["codeName"] = "_RETURN_"

#
# Go to inject position in Driver template
#
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
                line = regex.sub(param["codeName"], line)
                param_code += line

    return param_code

#
# Inject parameters deserialization
#
for index, param in enumerate(_params):
    driver_src += fetch_param_code(mapper_fname, param) + "\n"

#
# Compose the code to call Solution
#
inputs = ""
for index, param in enumerate(_params):
    inputs += (", " if index > 0 else "") + _params[index]["codeName"]
return_param = ""
if "return" in metadata["interface"]:
    _return = metadata["interface"]["return"]
    return_param = "{} {} = ".format(type_map[_return["type"]]["javaType"], _return["codeName"])
driver_src += " " * 12 + "{}(new Solution()).{}({});\n".format(return_param, _function, inputs)

#
# Serialize the Solution output
#
inputs = eval("metadata['interface']" + _output["from"] + "['codeName']")
inputs += ', "{}"'.format(_output["overrideSize"] if "overrideSize" in _output else "none")
serializer = type_map[eval("metadata['interface']" + _output["from"] + "['type']")]["serializer"]
driver_src += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Driver.java is for returning
#
driver_src += driver_tail
print(driver_src)

#
# Create Solution stub, and write to Solution.java
#
with open(interface_fname, "w") as interface_file:
    _interface = metadata["interface"]
    return_type = type_map[_interface["return"]["type"]]["javaType"] if "return" in _interface else "void"
    inputs = ""
    for index, param in enumerate(_params):
        inputs += (", " if index > 0 else "") + type_map[param["type"]]["javaType"] + " " + param["name"]
    interface_file.write('''
public class Solution {{
    public {} {}({}) {{

    }}
}}
'''.format(return_type, _function, inputs))
