import sys
import json
import re

import intfdesc as m
import typemap as t

tim = t.type_map

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
_function = metadata[m.itf][m.fnn]
_params = metadata[m.itf][m.pl]
_return = metadata[m.itf][m.rt]
_output = metadata[m.out]
for i, pm in enumerate(_params):
    _params[i][CODE_NAME] = PARAM_CN.format(str(i))
if m.rt in metadata[m.itf]:
    metadata[m.itf][m.rt][CODE_NAME] = RETURN_CN

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
            if line.strip() == tim[param[m.tp]][t.k_sep]:
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
if m.rt in metadata[m.itf]:
    _return = metadata[m.itf][m.rt]
    return_param = "{} {} = ".format(tim[_return[m.tp]][t.k_java_t], _return[CODE_NAME])
driver_code += " " * 12 + "{}(new Solution()).{}({});\n".format(return_param, _function, inputs)

#
# Serialize the output from solution
#
inputs = eval("{}['{}']{}['{}']".format("metadata", m.itf, _output[m.sc], CODE_NAME))
inputs += ', "{}"'.format(_output[m.osz] if m.osz in _output else m.non)
serializer = tim[eval("{}['{}']{}['{}']".format("metadata", m.itf, _output[m.sc], m.tp))][t.k_ser]
driver_code += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Driver.java is ready for returning
#
driver_code += driver_tail
print(driver_code)
