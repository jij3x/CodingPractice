"""
{
  "interface": {
    "functionName": "toSolve",
    "paramList": [
      {
        "name": "numbers",
        "type": ":array<:int>"
      },
      {
        "name": "target",
        "type": ":int"
      }
    ],
    "return": {
      "type": ":array<:int>"
    }
  },
  "output": {
    "from": "['return']",
    "cloneOf": "['paramList'][0]",
    "overrideSize": "none"
  }
}
"""

itf = "interface"
fnn = "functionName"
pl = "paramList"
nm = "name"
tp = "type"
rt = "return"
out = "output"
sc = "from"
osz = "overrideSize"
cln = "cloneOf"

non = "none"
