

import os
import sys,logging
sys.path.append(os.path.dirname(__name__))

from client import app


logging.basicConfig(
    filename = 'c:\\client-service.log',
    level = logging.DEBUG, 
    format = '[Service] %(levelname)-7.7s %(message)s'
)



import win32serviceutil
import win32service
import win32event
import servicemanager
import socket


class AppServerSvc (win32serviceutil.ServiceFramework):
    _svc_name_ = "TestService"
    _svc_display_name_ = "Test Service"

    def __init__(self,args):
        win32serviceutil.ServiceFramework.__init__(self,args)
        self.hWaitStop = win32event.CreateEvent(None,0,0,None)
        socket.setdefaulttimeout(60)

    def SvcStop(self):
        self.ReportServiceStatus(win32service.SERVICE_STOP_PENDING)
        win32event.SetEvent(self.hWaitStop)

    def SvcDoRun(self):
        servicemanager.LogMsg(servicemanager.EVENTLOG_INFORMATION_TYPE,
                              servicemanager.PYS_SERVICE_STARTED,
                              (self._svc_name_,''))
        self.main()

    def main(self):
        pass

if __name__ == '__main__':
    print(app)
    app.run(host="127.0.0.1", port=5050)
    win32serviceutil.HandleCommandLine(AppServerSvc)

