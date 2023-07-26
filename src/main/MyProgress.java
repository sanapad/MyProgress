package main;

import java.io.File;

public class MyProgress {
    public static void main(String[] args) {
        // it's current location by default
        String currentPath = System.getProperty("user.dir");

        Statistic statistic = new Statistic();

        currentPath = parsingCommandLineArgs(args, currentPath, statistic);

        // set a start directory
        final File currentDirectory = new File(currentPath);

        if (!currentDirectory.exists()) {
            System.out.println("There is not a catalog");
            return;
        }

        // call the function to traverse the directory
        traverseDirectory(currentDirectory, statistic);

        System.out.println(statistic);
    }

    private static String parsingCommandLineArgs(String[] args, String currentPath, Statistic statistic) {
        if (args.length == 0) { // output help description
            System.out.println("""
                    DESCRIPTION
                    	It’s a simple Java console utility for following some progress in processing any files:
                    	- it counts by file size and considers sub-folders
                    	- it assumes you have a consistent job with files "from the top down"
                    	- without any parameters, the program outputs the manual
                    	- use `ctrl + C` for cancel running
                                        
                    	Launch:
                    		./MyProgress.jar [number_of_finished_files] [target_folder]
                    	Where:
                    		[number_of_finished_files] - the number of finished files (requires)
                    		[target_folder]			   - the target folder (optional)
                    USING
                    	By default, it works in the call folder (the call from the Documents directory):
                    		user@laptop:~/Documents$ ./MyProgress.jar 2000
                    	output:
                    		PROGRESS: 33.62%
                    		Finished files: 2000 from 8892
                    		Total volume: 5747MB
                    		Remaining files size: 3815MB
                    	
                    	For example, you have directory `videos` in directory `/path_to_folder`:
                    		user@laptop:/path_to_folder$ ./MyProgress.jar 20 videos/
                    	output:
                    		PROGRESS: 20.90%
                    		Finished files: 20 from 108
                    		Total volume: 6718MB
                    		Remaining files size: 5314MB
                    	
                    	Another example:
                    		user@laptop:~$ ./MyProgress.jar 0
                    	output:
                    		PROGRESS: 0.00% \s
                    		Finished files: 0 from 205 \s
                    		Total volume: 11486MB \s
                    		Remaining files size: 11486MB
                    """);
            System.exit(0);
        } else if (args.length == 1) { // transfer the number of finished files
            try {
                statistic.setFinishedFiles(Long.parseLong(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("wrong argument of finished files");
                System.exit(0);
            }
        } else if (args.length == 2) { // transfer the number of finished files and the target directory
            try {
                statistic.setFinishedFiles(Long.parseLong(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("wrong argument of finished files");
                System.exit(0);
            }
            currentPath = args[1];
        } else {
            System.out.println("You must pass from 0 to 2 parameters");
            System.exit(0);
        }
        return currentPath;
    }

    private static void traverseDirectory(File thing, Statistic statistic) {
        // check if the object is a directory
        if (thing.isDirectory()) {
            // get the list of files and directories in the current directory
            File[] files = thing.listFiles();

            // if the list of files and directories isn't empty then traverse it recursively
            if (files != null) {
                for (File file : files) {
                    traverseDirectory(file, statistic); // recursive call for each file/directory
                }
            }
        }
        // passed object isn't a directory (it's a file)
        else {
            // except the running file "MyProgress.jar"
            if (!thing.getName().equals("MyProgress.jar")) {
                // count the volume of files and numbers of files
                statistic.setTotalVolume(statistic.getTotalVolume() + thing.length());
                statistic.incrementFileTotalNumbers();

                // count the finished files and remaining volume
                if (statistic.getCountFinishedFiles() > statistic.getFinishedFiles())
                    statistic.setRemainingVolume(statistic.getRemainingVolume() + thing.length());
                else statistic.incrementCountFinishedFiles();
            }
        }

    }
}
