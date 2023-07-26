package main;

public class Statistic {
    private long finishedFiles = 0;
    private int fileTotalNumbers = 0;
    private long totalVolume = 0;
    private long remainingVolume = 0;
    private int countFinishedFiles = 1;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // count and format the progress percent
        float remainingPercent = 100 - ((float) 100 / totalVolume * remainingVolume);
        String formatRemainingPercent = String.format("%.2f", remainingPercent) + "%";

        // check values
        if (finishedFiles < 0) result.append("ATTENTION: finished files less than 0\n");
        if (finishedFiles>fileTotalNumbers) result.append("ATTENTION: finished files more than total files\n");

        result.append("PROGRESS: ").append(formatRemainingPercent)
                .append("\nFinished files: ").append(finishedFiles).append(" from ").append(fileTotalNumbers)
                .append("\nTotal volume: ").append(totalVolume / 1024 / 1024).append("MB")
                .append("\nRemaining files size: ").append(remainingVolume / 1024 / 1024).append("MB");


        return result.toString();
    }

    public long getFinishedFiles() {
        return finishedFiles;
    }

    public void setFinishedFiles(long finishedFiles) {
        this.finishedFiles = finishedFiles;
    }

    public void incrementFileTotalNumbers() {
        this.fileTotalNumbers += 1;
    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public long getRemainingVolume() {
        return remainingVolume;
    }

    public void setRemainingVolume(long remainingVolume) {
        this.remainingVolume = remainingVolume;
    }

    public int getCountFinishedFiles() {
        return countFinishedFiles;
    }

    public void incrementCountFinishedFiles() {
        this.countFinishedFiles++;
    }


}
