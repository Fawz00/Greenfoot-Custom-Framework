# Clean all file with the .ctxt and .class extension in the directory
# Usage: python clean_dir.py

import os

def clean_dir():
    for file in os.listdir("."):
        if file.endswith(".ctxt") or file.endswith(".class"):
            os.remove(file)
            print("Removed: " + file)

if __name__ == "__main__":
    clean_dir()