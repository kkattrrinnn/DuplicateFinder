import java.util.ArrayList;
import java.io.File;
import java.util.Objects;

public class DuplicateFinder {
    public static MainPage MP;
    public static SecondaryPage SP;
    static ArrayList<ArrayList<FileObject>> listOfAllDuplicates;
    static int size = 0;

    public static void main(String[] args) {
        listOfAllDuplicates = new ArrayList<>();
        MP = new MainPage();
    }

    // выполнение программы после нажатия на кнопку "поиск"
    public static void PerformTheSearchFunction() {
        String fileName = MainPage.GetFileName();
        String directoryName = MainPage.GetDirectoryName();
        ArrayList<FileObject> tempFiles = new ArrayList<>();
        ArrayList<Long> usedSizesOfFiles = new ArrayList<>();
        Long sizeOfFile;
        if (fileName.equals("")) {
            File dir = new File(directoryName);
            ArrayList<FileObject> files = new ArrayList<>();
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                DepthFirstSearch(item, files);
            }
            SortFilesBySize(files);                         // сортировка найденных файлов по размеру
            // группировка файлов по размеру с дальнейшим поиском дубликатов в каждой группе
            for (int i = 0; i < files.size(); i++) {            // реализация группировки
                tempFiles.clear();
                sizeOfFile = files.get(i).size;
                if (usedSizesOfFiles.contains(sizeOfFile)) {
                    continue;
                } else {
                    usedSizesOfFiles.add(sizeOfFile);           // файлы одного размера помещаются во временный список
                    tempFiles.add(files.get(i));
                    for (int j = i + 1; j < files.size(); j++) {
                        if (files.get(j).size > sizeOfFile) {
                            break;
                        } else if (files.get(j).size.longValue() == sizeOfFile){
                            tempFiles.add(files.get(j));
                        }
                    }
                    if (tempFiles.size()>1) {
                        SearchForDuplicates(tempFiles);
                    }
                }
            }
        } else {
            FileObject originalFile = new FileObject(fileName);
            if (!originalFile.exists()) {
                MP.secondLineOfTopTable.setText("  Проверьте введённые данные");
            } else {
                Long originalFileSize = originalFile.size;
                File dir = new File(directoryName);
                ArrayList<FileObject> files = new ArrayList<>();
                for (File item : Objects.requireNonNull(dir.listFiles())) {
                    DepthFirstSearch(item, files);
                }
                SortFilesBySize(files);                         // сортировка найденных файлов по размеру
                tempFiles.clear();
                for (int i = 0; i < files.size(); i++) {            // поиск файлов с тем же размером
                    sizeOfFile = files.get(i).size;
                    if (sizeOfFile.equals(originalFileSize)) {
                        tempFiles.add(files.get(i));
                    }
                }
                if (tempFiles.size() > 0) {
                    SearchForDuplicates(tempFiles, originalFile.getHash());
                }
            }
        }
        if (listOfAllDuplicates.size() > 0) {
            MP.dispose();
            SP = new SecondaryPage(listOfAllDuplicates, size);
        }
    }

///Users/kkattrrinnn/Documents/Studies(III:II)/Производственная практика/Dogovor_na_praktiku_2022 — копия 2.docx
// создание списка всех файлов в начальном каталоге (поиск в глубину)
    public static void DepthFirstSearch(File object, ArrayList<FileObject> listOfFiles) {
        try {
            if (object.isFile()) {
                listOfFiles.add(new FileObject(object.toString()));
            } else if (object.isDirectory()) {
                for (File ob : Objects.requireNonNull(object.listFiles())) {
                    if (ob.isHidden()) {
                        continue;
                    } else {
                        DepthFirstSearch(ob, listOfFiles);
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

// Сортировка файлов по размеру для дальнейшей группировки
    public static void SortFilesBySize(ArrayList<FileObject> listOfFiles) {
        FileObject temp_file;
        int index_max;
        int n = listOfFiles.size()-1;
        for (int i = n; i > 0; i--) {
            index_max = 0;
            for (int j = 1; j <= i; j++) {
                if (listOfFiles.get(j).size>listOfFiles.get(index_max).size) {
                    index_max = j;
                }
                temp_file = listOfFiles.get(i);                     // перестановка элементов
                listOfFiles.set(i, listOfFiles.get(index_max));
                listOfFiles.set(index_max, temp_file);
            }
        }
    }

// Поиск дубликатов в списке файлов
    public static void SearchForDuplicates(ArrayList<FileObject> listOfFiles, String originalFileHash) {
        int n = listOfFiles.size();
        ArrayList<FileObject> duplicates = new ArrayList<>();
        ArrayList<FileObject> usedFiles = new ArrayList<>();
        String hash;
        String hash2;
        if (originalFileHash.equals("")) {
            for (int i = 0; i < n; i++) {
                if (usedFiles.contains(listOfFiles.get(i))) {
                    continue;
                } else {
                    duplicates.clear();
                    hash = listOfFiles.get(i).getHash();
                    duplicates.add(listOfFiles.get(i));
                    usedFiles.add(listOfFiles.get(i));

                    for (int j = i + 1; j < n; j++) {
                        hash2 = listOfFiles.get(j).getHash();
                        if (hash2.equals(hash)) {
                            duplicates.add(listOfFiles.get(j));
                            usedFiles.add(listOfFiles.get(j));
                        }
                    }
                    if (duplicates.size() > 1) {
                        listOfAllDuplicates.add(duplicates);
                        size += duplicates.size();
                    }
                }
            }
        } else {
            hash = originalFileHash;
            duplicates.clear();
            for (int i = 0; i < n; i++) {
                hash2 = listOfFiles.get(i).getHash();
                if (hash.equals(hash2)) {
                    duplicates.add(listOfFiles.get(i));
                }
            }
            if (duplicates.size() > 0) {
                listOfAllDuplicates.add(duplicates);
                size += duplicates.size();
            }
        }
    }

    public static void SearchForDuplicates(ArrayList<FileObject> listOfFiles) {
        SearchForDuplicates(listOfFiles, "");
    }

    public static void DeleteFiles(ArrayList<FileObject> filesToDelete) {
        for (File ob : filesToDelete) {
            ob.delete();
        }
    }
}