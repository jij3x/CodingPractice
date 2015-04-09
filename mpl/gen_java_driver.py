import sys
import json
import re

import intfdesc as m
import typemap as t

tim = t.type_map

DRVTML_FNM = "java.driver.template"

CODE_NAME = "codeName"
SOL_RETURN_CN = "_RETURN_"
IP_RETURN_CN = "_IP_RETURN_{}_"
OP_RETURN_CN = "_OP_RETURN_{}_"
PARAM_CN = "_PARAM_{}_"
PARAM_DS_POS = "// param deserialization code inject here"
SOLVING_POS = "// solution invoking code inject here"
OUTPUT_S_POS = "// result serialization code inject here"

#
# Read problem description, and compose metadata
#
with open(sys.argv[1]) as metadata_file:
    metadata = json.load(metadata_file)
for i in range(len(metadata[m.INP])):
    metadata[m.INP][i][CODE_NAME] = PARAM_CN.format(str(i))

if m.RT not in metadata[m.SOL]:
    metadata[m.SOL][m.RT] = {m.TYP: t.VOID}
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
for arg in metadata[m.INP]:
    deser = tim[arg[m.TYP]][t.P_DES]
    rtype = tim[arg[m.TYP]][t.P_JAVA_T]
    param_deser_code += " " * 12 + "{} {} = Serializer.{}(tokenizer);\n".format(rtype, arg[CODE_NAME], deser)


def eval_prop(s):
    prop = re.sub(r"\[\[\"(\w+)\"\]\]", "[\g<1>]", re.sub(r"\.", "", re.sub(r"(\w+)", "[\"\g<1>\"]", s)))
    return eval("metadata" + prop)

#
# Compose the code to process input
#


#
# Compose the code to call Solution
#
inputs = ""
for i, par in enumerate(metadata[m.SOL][m.PAR]):
    inputs += (", " if i > 0 else "") + eval_prop(par)[CODE_NAME]
ret = metadata[m.SOL][m.RT]
return_par = "{} {} = ".format(tim[ret[m.TYP]][t.P_JAVA_T], ret[CODE_NAME]) if ret[m.TYP] != t.VOID else ""
solving_code = " " * 12 + "{}(new Solution()).{}({});\n".format(return_par, metadata[m.SOL][m.FN], inputs)

#
# Compose the code to process output
#


#
# Compose the code to serialize the Solution return
#
result_ser_code = ""
inputs = eval_prop(metadata[m.OUT])[CODE_NAME]
serializer = tim[eval_prop(metadata[m.OUT])[m.TYP]][t.P_SER]
result_ser_code += " " * 12 + "printWriter.println(Serializer.{}({}));\n".format(serializer, inputs)

#
# Inject the code into Driver template
#
with open(DRVTML_FNM) as driver_template:
    driver_code = re.sub(r"[ \t]*" + re.escape(PARAM_DS_POS) + r".*?\n", param_deser_code, driver_template.read())
    driver_code = re.sub(r"[ \t]*" + re.escape(SOLVING_POS) + r".*?\n", solving_code, driver_code)
    driver_code = re.sub(r"[ \t]*" + re.escape(OUTPUT_S_POS) + r".*?\n", result_ser_code, driver_code)

print(driver_code)
