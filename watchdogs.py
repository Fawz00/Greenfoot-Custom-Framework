import os
import shutil
import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler

# Path ke folder proyek Greenfoot
GREENFOOT_PROJECT_PATH = "C:/Users/fawwazhp/Documents/Project/Greenfoot/星の秘密を求めるカギよ、開拓者たちに真なる祝福を。"
GAME_PACKAGE = "greenfoot_game"
SRC_FOLDER = os.path.join(GREENFOOT_PROJECT_PATH, "src")

class GreenfootSyncHandler(FileSystemEventHandler):
    def on_modified(self, event):
        if event.src_path.endswith(".java"):
            self.sync_file(event.src_path)

    def on_created(self, event):
        if event.src_path.endswith(".java"):
            self.sync_file(event.src_path)

    def sync_file(self, file_path):
        """Menyalin file Java dari subfolder ke folder utama Greenfoot, menghapus deklarasi package dan local import"""
        filename = os.path.basename(file_path)
        dest_path = os.path.join(GREENFOOT_PROJECT_PATH, filename)

        with open(file_path, "r", encoding="utf-8") as src_file:
            lines = src_file.readlines()

        new_lines = []
        for line in lines:
            stripped = line.strip()
            # Hapus baris package
            if stripped.startswith("package "):
                continue
            # Hapus import yang mengarah ke package lokal
            if stripped.startswith("import ") and GAME_PACKAGE in stripped:
                continue
            # Sisanya tetap ditulis
            new_lines.append(line)

        with open(dest_path, "w", encoding="utf-8") as dest_file:
            dest_file.writelines(new_lines)

        print(f"📄 {filename} telah disalin ke {dest_path} (package & local import dihapus)")


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