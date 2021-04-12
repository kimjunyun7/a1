import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    private static final String CSV_SEPERATOR = ",";


    // Load a file of enrolments
    public void loadFile(String file) {
        BufferedReader reader = null;
        try {
            String line = "";
            reader = new BufferedReader(new FileReader(file));
            //Read file line by line
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(CSV_SEPERATOR);
                Enrolment.studentEnrolmentList.add(new StudentEnrolment(
                        new Student(tokens[0], tokens[1], tokens[2]),
                        new Course(tokens[3], tokens[4], tokens[5]),
                        tokens[6]));
            }
            Enrolment.makeStudentList();
            Enrolment.makeCourseList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Write on file of enrolments
    public void writeFile(String file) {
        BufferedWriter writer = null;
        try{
            writer = Files.newBufferedWriter(Paths.get(file), StandardCharsets.UTF_8);
//            writer = new BufferedWriter(new FileWriter(file));

            for(StudentEnrolment se : Enrolment.studentEnrolmentList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(se.getStudent().getId());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getStudent().getName());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getStudent().getBirthdate());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getCourse().getId());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getCourse().getName());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getCourse().getNumOfCredit());
                oneLine.append(CSV_SEPERATOR);
                oneLine.append(se.getSemester());
                writer.write(oneLine.toString());
                writer.newLine();
            }
            writer.flush();

        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(writer != null){
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
