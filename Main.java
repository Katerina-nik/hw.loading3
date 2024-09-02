
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class Main {
    public static void main(String[] args) {
        openZip("/Users/ekaterinapyhtina/Desktop/Games/saveGames/zip.zip");
        System.out.println(openProgress("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save1.dat"));


    }
    private static void openZip(String fileZip){

        try(ZipInputStream zin  = new ZipInputStream(new FileInputStream(fileZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null){
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()){
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static GameProgress openProgress(String fileZip) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(fileZip);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;

    }
}
