import java.nio.file.Files;
import java.util.ArrayList;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class DuplicateFinder {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("File 1//Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 2/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 3/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 4/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 5/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 6/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 7/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 8/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 9/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 10/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 11/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 12/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 13/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 14/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 15/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 16/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 17/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 18/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 19/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 20/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 21/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 21/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");
        names.add("File 22/Users/kkattrrinnn/Documents/ОПП III II/Сетевое программирование/pdf");


        //MainPage MP = new MainPage();
        //SecondaryPage SP = new SecondaryPage(names);

        Scanner in = new Scanner(System.in);
        ArrayList<FileObject> files = new ArrayList<>();
        ArrayList<FileObject> temp_files = new ArrayList<>();
        ArrayList<FileObject> files_to_delete = new ArrayList<>();
        ArrayList<Long> used_sizes = new ArrayList<>();
        Long size;
        String name_dir;
        System.out.println("Введите директорию в формате C:\\Program Files: ");
        name_dir = in.nextLine();
        File dir1 = new File(name_dir);

        for (File item : dir1.listFiles()) {
            Search_all_files(item, files);
        }

        Sort_size(files);

        for (int i=0; i<files.size(); i++) {
            temp_files.clear();

            size = files.get(i).size.longValue();

            if (used_sizes.contains(size)) {
                continue;
            } else {
                used_sizes.add(size);

                temp_files.add(files.get(i));

                for (int j=i+1; j<files.size(); j++) {
                    if (files.get(j).size.longValue()>size) {
                        break;
                    } else if (files.get(j).size.longValue() == size){
                        temp_files.add(files.get(j));
                    }
                }

                if (temp_files.size()>1) {
                    Search_duplicates(temp_files, files_to_delete);
                    System.out.println();
                }
            }
        }

        if (files_to_delete.size()>0) {
            System.out.println(files_to_delete);
            for (int i=0; i<files_to_delete.size(); i++) {
                files_to_delete.get(i).delete();
            }
            System.out.println("Копии были успешно удалены. ");
        } else {
            System.out.println("Файлы для удаления не найдены");
        }
    }
//<orderEntry type="library" name="commons-codec-1.15" level="project" />
    public static void Search_all_files (File dir, ArrayList<FileObject> array_files) {

        try {
            if (dir.isFile()) {
                array_files.add(new FileObject(dir.toString()));
            } else if (dir.isDirectory()) {
                for (File ob : dir.listFiles()) {
                    if (ob.isHidden()) {
                        continue;
                    } else {
                        Search_all_files(ob, array_files);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.print(' ');
        }
    }

    public static void Sort_size(ArrayList<FileObject> Files) {
        int index_max;
        int n = Files.size()-1;
        FileObject temp_file;

        for (int i=n; i>0; i--) {
            index_max = 0;

            for (int j=1; j<=i; j++) {
                if (Files.get(j).size>Files.get(index_max).size) {
                    index_max = j;
                }
                temp_file = Files.get(i);
                Files.set(i, Files.get(index_max));
                Files.set(index_max, temp_file);
            }
        }
    }

    public static void Search_duplicates(ArrayList<FileObject> array_files, ArrayList<FileObject> files_to_del) {
        Scanner in2 = new Scanner(System.in);
        int n = array_files.size();
        int answer;
        int original;
        ArrayList<FileObject> duplicates = new ArrayList<>();
        ArrayList<FileObject> used_files = new ArrayList<>();
        String hash;
        String hash2;

        for (int i=0; i<n; i++) {
            if (files_to_del.contains(array_files.get(i)) | used_files.contains(array_files.get(i))) {
                continue;
            } else {
                duplicates.clear();
                hash = array_files.get(i).get_hash();
                duplicates.add(array_files.get(i));
                used_files.add(array_files.get(i));

                for (int j = i + 1; j < n; j++) {
                    hash2 = array_files.get(j).get_hash();
                    if (hash2.equals(hash)) {
                        duplicates.add(array_files.get(j));
                        used_files.add(array_files.get(j));
                    }
                }

                if (duplicates.size()>1) {
                    System.out.println("Найденные дубликаты: ");
                    for (int k=0; k<duplicates.size(); k++) {
                        System.out.println((k+1) + ": " +duplicates.get(k) + "---" + duplicates.get(k).get_hash());

                    }
                    System.out.print("Для удаления копий введите 1, иначе  - 0: ");
                    answer = in2.nextInt();
                    if (answer == 1) {
                        System.out.print("Введите номер файла, который следует оставить: ");
                        original = in2.nextInt()-1;
                        for (int k = 0; k < duplicates.size(); k++) {
                            if (k != original) {
                                files_to_del.add(duplicates.get(k));
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean Search() {
        return false;
    }
}