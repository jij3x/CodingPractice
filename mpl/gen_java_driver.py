import sys
import json
import re

import intfdesc as md
import typemap as tm

tim = tm.type_map

MAPPER_FNM = "Mapper.java"
DRVTML_FNM = "drv_template.java"

CODE_NAME = "codeName"
RETURN_CN = "_RETURN_"
PARAM_CN = "_PARAM_{}_"
INJECT_POS = "// inject here"

#
# Read problem description, and compose metadata
#
with open(sys.argv[1]) as metadata_file:
    metadata = json.load(metadata_file)
_function = metadata[md.itf][md.fnn]
_params = metadata[md.itf][md.pl]
_return = metadata[md.itf][md.rt]
_output = metadata[md.out]
for i, pm in enumerate(_params):
    _params[i][CODE_NAME] = PARAM_CN.format(str(i))
if md.rt in metadata[md.itf]:
    metadata[md.itf][md.rt][CODE_NAME] = RETURN_CN

#
# Go to inject position in Driver template
#
driver_code = ""
driver_tail = ""
with open(DRVTML_FNM) as driver_template:
    is_tail = False
    for line in driver_template:
        if line.strip() == INJECT_POS:
            is_tail = True
        elif not is_tail:
            driver_code += line
        else:
            driver_tail += line


def fetch_param_deser_code(template_fname, param):
    param_code = ""

    with open(template_fname) as mapper_file:
        regex = re.compile(r"_PARAM_")
        saw_seperator = False
        for line in mapper_file:
            if line.strip() == tim[param[md.tp]][tm.k_sep]:
                if saw_seperator:
                    break
                saw_seperator = True
            elif saw_seperator:
                line = regex.sub(param[CODE_NAME], line)
                param_code += line

    return param_code

#
# Inject parameters deserialization code
#
for i, pm in enumerate(_params):
    driver_code += fetch_param_deser_code(MAPPER_FNM, pm) + "\n"

#
# Compose the code to call Solution
#
inputs = ""
for i, pm in enumerate(_params):
    inputs += (", " if i > 0 else "") + _params[i][CODE_NAME]
return_param = ""
if md.rt in metadata[md.itf]:
    _return = metadata[md.itf][md.rt]
    return_param = "{} {} = ".format(tim[_return[md.tp]][tm.k_java_t], _return[CODE_NAME])
driver_code += " " * 12 + "{}(new Solution()).{}({});\n".format(return_param, _function, inputs)

#
# Serialize the output from solution
#
inputs = eval("{}['{}']{}['{}']".format("metadata", md.itf, _output[md.sc], CODE_NAME))
inputs += ', "{}"'.format(_output[md.osz] if md.osz in _output else md.non)
serializer = tim[eval("{}['{}']{}['{}']".format("metadata", md.itf, _output[md.sc], md.tp))][tm.k_ser]
driver_code += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Driver.java is ready for returning
#
driver_code += driver_tail
print(driver_code)
