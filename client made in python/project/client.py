
import threading
import sys,os,time
from subprocess import check_output,CalledProcessError


#add current path 
sys.path.append(os.path.dirname(__name__))

import jinja2.ext

from flask import Flask,session,request, make_response
from flask import g, request, redirect, url_for


from shutdown import shutdownL

from hashlib import sha224

from functools import wraps

from werkzeug import secure_filename

import win32api




#-------------------------------------------------------------------


app = Flask(__name__)

if getattr(sys, 'frozen', False):
    # frozen
    app_root = os.path.dirname(sys.executable)
else:
    # unfrozen
    app_root = os.path.dirname(os.path.realpath(__file__))


#-------------------------------------------------------------------
argv = sys.argv
authentication_key = "946b92d1d845be3c0f0df816ec66658383352c9d17d92ae23056bad2"
app.secret_key = authentication_key
#app_root = os.path.dirname(os.path.abspath(__file__))
root_pass , root_user =  sha224(b"med").hexdigest(),sha224(b"med").hexdigest()
login_url = "/"
shared = "c://shared/"

if not os.path.exists(shared):
	os.mkdir(shared)	



#-------------------------------------------------------------------


def login_required(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if not is_connected():
            return redirect(login_url)
        return f(*args, **kwargs)
    return decorated_function


#-------------------------------------------------------------------


def authenticate(request):

	global authentication_key,root_user,root_pass

	if request.method =="POST":
		try:
			
			_user = sha224(request.form['username'].encode()).hexdigest()
			_pass = sha224(request.form['password'].encode()).hexdigest()
			#key = request.form['key']
			
		except Exception as e :
			app.logger.debug(e)
			return False
		if _user == root_user and _pass == root_pass :#and sha224(key.encode()).hexdigest() ==  authentication_key:
			key = sha224((_user+_pass+str(time.time())).encode()).hexdigest()
			os.environ['key']= key
			return key
	return False



def is_connected():
	d=request.cookies.get('key',False)
	try:
		os.environ[d] 
		return True
	except Exception :
		return False



def show_msg(title="test msg",msg="msg from admin"):
	win32api.MessageBox(0,msg,title)
	win32api.MessageBeep(0)



@app.route('/authenticate',methods=["POST",])
def main_auth():
	if is_connected():
		return True
	d = authenticate(request)
	#print("authenticated with key: "+d)
	if d :
		resp = make_response("welcome")
		resp.set_cookie(b'key',d)
		return resp
	return "failed to conenct check authentication "

@login_required
@app.route('/execute', methods = ["GET","POST",])
def execute():
	if request.method== "GET":
		cmd = request.args.get('action',"dir")
	else:
		cmd = request.form["action"]
	try:
		p = check_output([cmd,],shell=True)
	except CalledProcessError :
		return "subprocess.CalledProcessError "
	return p


@login_required
@app.route('/behaviour/lock')
def lock():
	import ctypes
	ctypes.windll.user32.LockWorkStation()
	return "done"


@login_required
@app.route('/behaviour/restart')
def restart():
	import os
	os.system("shutdown -r -t 1")
	return "done"


@login_required
@app.route('/behaviour/shut')
def shut():
	shutdownL()
	return "done"


@login_required
@app.route("/c_msg",methods=["POST",])
def create_msg():
	print(request.cookies)	
	if  not request.cookies:
		print("no cookies")
		return "fail"

	title = request.form.get('title')
	msg = request.form.get('msg')
	print(title,request.form)
	if title and msg:
		t = threading.Thread(target=show_msg,args=(title,msg,))
	else:
		t = threading.Thread(target=show_msg)
	t.start()
	return "done"




@app.route('/new_file',methods=["POST",])
def file_handler():
	if request.method == 'POST':
		f = request.files['myfile']
		pathe = os.path.join(shared,secure_filename(f.filename))
		f.save(pathe)
		win32api.MessageBox(0,"file received","you have received a file saved in : "+ pathe)
		return 'file uploaded successfully'


#-------------------------------------------------------------------


@app.route('/test',methods=["POST",])
def test_f():
	print(request.form)
	return "done"



#-------------------------------------------------------------------
if __name__ == '__main__':
    #app.run(debug=True)
    print(argv)
    app.run(host=argv[1], port=int(argv[2]))
    
