from urllib import request as r
from urllib import parse

data ={"username":"med","pass":"med"}
data = parse.urlencode(data).encode()
resp = r.urlopen("http://127.0.0.1:5000/authenticate",data=data)
print(resp.read())


