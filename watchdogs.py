import os
import shutil
import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler

# Path ke folder proyek Greenfoot
GREENFOOT_PROJECT_PATH = "C:/Users/fawwazhp/Documents/Project/Greenfoot/星の秘密を求めるカギよ、開拓者たちに真なる祝福を。"
SRC_FOLDER = os.path.join(GREENFOOT_PROJECT_PATH, "src")  # Folder tempat kita menyusun kode

class GreenfootSyncHandler(FileSystemEventHandler):
    def on_modified(self, event):
        if event.src_path.endswith(".java"):
            self.sync_file(event.src_path)

    def on_created(self, event):
        if event.src_path.endswith(".java"):
            self.sync_file(event.src_path)

    def sync_file(self, file_path):
        """Menyalin file Java dari subfolder ke folder utama Greenfoot"""
        filename = os.path.basename(file_path)
        dest_path = os.path.join(GREENFOOT_PROJECT_PATH, filename)

        shutil.copy(file_path, dest_path)
        print(f"📄 {filename} telah disalin ke {dest_path}")

if __name__ == "__main__":
    event_handler = GreenfootSyncHandler()
    observer = Observer()
    observer.schedule(event_handler, SRC_FOLDER, recursive=True)
    observer.start()

    print("🔄 Auto-sync Greenfoot berjalan... Tekan CTRL+C untuk berhenti.")
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()
