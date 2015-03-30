import sys
import json
import re

INT = "int"
ARRAY = "array"
LIST = "list"
INT_ARRAY = "int_array"
INT_LIST = "int_list"

metadata_fn = sys.argv[1]
mapper_fn = "Mapper.java"
drv_tml_fn = "Driver.java.template"

with open(metadata_fn) as metadata_file:
    metadata = json.load(metadata_file)

_action = metadata["interface"]["actionName"]
_params = metadata["interface"]["paramList"]
_return = metadata["interface"]["return"]
_in_fn = metadata["inFile"]
_out_fn = metadata["outFile"]

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


def determineType(paramType):
    if paramType == INT:
        return INT
    return INT_ARRAY


def fetchParamCode(template_fn, paramType, idx):
    paramCode = ""
    delimiter = ""
    if paramType == INT:
        delimiter = "// |Int"
    elif paramType == INT_ARRAY:
        delimiter = "// |IntArray"

    with open(template_fn) as mapper_file:
        regex = re.compile(r"(\$[a-zA-Z0-9_]*)")
        sawDelimiter = False
        for line in mapper_file:
            if line.strip() == delimiter:
                if sawDelimiter:
                    break
                sawDelimiter = True
            elif sawDelimiter:
                line = regex.sub("\g<1>"+str(idx), line)
                paramCode += line

    return paramCode

for index, param in enumerate(_params):
    print(fetchParamCode(mapper_fn, determineType(param["type"]), index+1))

print(driverTail)
