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
PARAM_DS_POS = "// param deserialization code inject here"
OUTPUT_S_POS = "// result serialization code inject here"

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
metadata[m.itf][m.rt][CODE_NAME] = RETURN_CN


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
# Fetch parameters deserialization code
#
param_deser_code = ""
for i, pm in enumerate(_params):
    param_deser_code += fetch_param_deser_code(MAPPER_FNM, pm) + "\n"

#
# Compose the code to call Solution
#
inputs = ""
for i, pm in enumerate(_params):
    inputs += (", " if i > 0 else "") + _params[i][CODE_NAME]
return_pm = "{} {} = ".format(tim[_return[m.tp]][t.k_java_t], _return[CODE_NAME]) if _return[m.tp] != t.t_void else ""
result_ser_code = " " * 12 + "{}(new Solution()).{}({});\n".format(return_pm, _function, inputs)

#
# Compose the code to serialize the Solution return
#
inputs = eval("{}['{}']{}['{}']".format("metadata", m.itf, _output[m.sc], CODE_NAME))
inputs += ', "{}"'.format(_output[m.osz] if m.osz in _output else m.non)
serializer = tim[eval("{}['{}']{}['{}']".format("metadata", m.itf, _output[m.sc], m.tp))][t.k_ser]
result_ser_code += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Inject the code into Driver template
#
with open(DRVTML_FNM) as driver_template:
    driver_code = re.sub(r"[ \t]*" + re.escape(PARAM_DS_POS) + r".*?\n", param_deser_code, driver_template.read())
    driver_code = re.sub(r"[ \t]*" + re.escape(OUTPUT_S_POS) + r".*?\n", result_ser_code, driver_code)

print(driver_code)



import sys
import json
import re

import intfdesc as m
import typemap as t

tim = t.type_map

MAPPER_FNM = "Mapper.java"
DRVTML_FNM = "drv_template.java"

CODE_NAME = "codeName"
SOL_RETURN_CN = "_RETURN_"
IP_RETURN_CN = "_IP_RETURN_{}_"
OP_RETURN_CN = "_OP_RETURN_{}_"
PARAM_CN = "_PARAM_{}_"
PARAM_DS_POS = "// param deserialization code inject here"
OUTPUT_S_POS = "// result serialization code inject here"

#
# Read problem description, and compose metadata
#
with open(sys.argv[1]) as metadata_file:
    metadata = json.load(metadata_file)
for i in range(len(metadata[m.INP])):
    metadata[m.INP][i][CODE_NAME] = PARAM_CN.format(str(i))

if m.RT not in metadata[m.SOL]:
    metadata[m.SOL][m.RT] = {m.TYP: t.t_void}
metadata[m.SOL][m.RT][CODE_NAME] = SOL_RETURN_CN

if m.IPR in metadata:
    for i in range(len(metadata[m.IPR])):
        metadata[m.IPR][i][m.RT][CODE_NAME] = IP_RETURN_CN.format(str(i))

if m.OPR in metadata:
    for i in range(len(metadata[m.OPR])):
        metadata[m.OPR][i][m.RT][CODE_NAME] = OP_RETURN_CN.format(str(i))

#
# Fetch inputs deserialization code
#
param_deser_code = ""
with open(MAPPER_FNM) as mapper_file:
    par_rx = re.compile(r"_PARAM_")
    mapper_src = mapper_file.read()
    for arg in metadata[m.INP]:
        seperator = re.escape((tim[arg[m.TYP]][t.k_sep]))
        sep_rx = r"[ \t]*{}[ \t]*\n(.*?)[ \t]*{}[ \t]*\n".format(seperator, seperator)
        param_deser_code += par_rx.sub(arg[CODE_NAME], re.search(sep_rx, mapper_src, re.S).group(1) + "\n")

print(param_deser_code)


#
# Compose the code to call Solution
#
inputs = ""
for i, pm in enumerate(_params):
    inputs += (", " if i > 0 else "") + _params[i][CODE_NAME]
return_pm = "{} {} = ".format(tim[_return[m.T]][t.k_java_t], _return[CODE_NAME]) if _return[m.T] != t.t_void else ""
result_ser_code = " " * 12 + "{}(new Solution()).{}({});\n".format(return_pm, _function, inputs)

#
# Compose the code to serialize the Solution return
#
inputs = eval("{}['{}']{}['{}']".format("metadata", m.S, _output[m.sc], CODE_NAME))
inputs += ', "{}"'.format(_output[m.osz] if m.osz in _output else m.non)
serializer = tim[eval("{}['{}']{}['{}']".format("metadata", m.itf, _output[m.sc], m.tp))][t.k_ser]
result_ser_code += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Inject the code into Driver template
#
with open(DRVTML_FNM) as driver_template:
    driver_code = re.sub(r"[ \t]*" + re.escape(PARAM_DS_POS) + r".*?\n", param_deser_code, driver_template.read())
    driver_code = re.sub(r"[ \t]*" + re.escape(OUTPUT_S_POS) + r".*?\n", result_ser_code, driver_code)

print(driver_code)






>>> str = "inProcr[0].return"
>>> print(re.sub(r"(\w+)", "[\"\g<1>\"]", str))
>>> print(re.sub(r"\[\[\"(\w+)\"\]\]", "[\g<1>]", str))
>>> print(re.sub(r"\.", "", str))
