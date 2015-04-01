import sys
import json
import re

INT = ":int"
ARRAY = ":array"
LIST = ":list"
INT_ARRAY = "int_array"
INT_LIST = "int_list"

metadata_fn = sys.argv[1]
mapper_fn = "Mapper.java"
drv_tml_fn = "Driver.java.template"

with open(metadata_fn) as metadata_file:
    metadata = json.load(metadata_file)

_function = metadata["interface"]["functionName"]
_params = metadata["interface"]["paramList"]
_return = metadata["interface"]["return"]
_output = metadata["output"]
_in_fn = "user.in"
_out_fn = "user.out"

driverTail = ""
with open(drv_tml_fn) as driver_template:
    toTail = False
    for line in driver_template:
        if line.strip() == "// inject here":
            toTail = True
        elif not toTail:
            print(line, end="")
        else:
            driverTail += line


def determine_type(param_type):
    if param_type == INT:
        return INT
    return INT_ARRAY


def fetch_param_code(template_fn, param_type, idx):
    param_code = ""
    delimiter = ""
    if param_type == INT:
        delimiter = "// |:int"
    elif param_type == INT_ARRAY:
        delimiter = "// |:intArray"

    with open(template_fn) as mapper_file:
        regex = re.compile(r"(_[a-zA-Z0-9_]*)")
        saw_delimiter = False
        for line in mapper_file:
            if line.strip() == delimiter:
                if saw_delimiter:
                    break
                saw_delimiter = True
            elif saw_delimiter:
                line = regex.sub("\g<1>" + str(idx) + "_", line)
                param_code += line

    return param_code


def fetch_output_code():
    print("hello")


for index, param in enumerate(_params):
    print(fetch_param_code(mapper_fn, determine_type(param["type"]), index))

call_solution = " " * 12
if _return["type"] != ":void":
    call_solution += "int[] _RETURN_ = "
call_solution += "(new Solution())." + _function + "("
for index, param in enumerate(_params):
    if index > 0:
        call_solution += ", "
    call_solution += "_PARAM_" + str(index) + "_"
call_solution += ");"
print(call_solution)

serialize_output = " " * 12 + "printWriter.println("
serializer = "Serializer.serialize_int_array"
serialize_output += serializer + "(" + _output["from"]
if "specialSize" in _output:
    serialize_output += ", " + _output["specialSize"]
serialize_output += "));"
print(serialize_output)

print(driverTail)
