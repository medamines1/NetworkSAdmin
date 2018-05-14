from cx_Freeze import setup, Executable

# On appelle la fonction setup
setup(
    name = "votre_programme",
    version = "1",
    description = "Votre programme",
    options = {
        'build_exe': {
            'packages': ['encodings', 'asyncio','win32timezone',
            'win32serviceutil','win32service','multiprocessing',
            'win32event','win32evtlogutil','servicemanager']
        },
    },
    executables = [Executable(r'F:\python\project\client.py',base = "Win32GUI")],
)