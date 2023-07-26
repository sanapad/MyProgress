## Description
It’s a simple Java console utility for following some progress in processing any files:
- it counts by *file size* and considers sub-folders
- it assumes you have a consistent job with files "from the top down"
- it may have 1 to 2 options: the number of finished files (requires) and the target folder (optional)
- without any parameters, the program outputs the manual
- use `ctrl + C` for cancel running

Launch:
```bash
./MyProgress.jar [number_of_finished_files] [target_folder]
```
Where:
- `[number_of_finished_files]` - the number of finished files (**requires**)
- `[target_folder]` - the target folder (optional)

## Using
By default, it works in the call folder (the call from the Documents directory):
```bash
user@laptop:~/Documents$ ./MyProgress.jar 2000
```
output:
```bash
PROGRESS: 33.62%
Finished files: 2000 from 8892
Total volume: 5747MB
Remaining files size: 3815MB
```
For example, you have directory `videos` in directory `/path_to_folder`:
```bash
user@laptop:/path_to_folder$ ./MyProgress.jar 20 videos/
```
output:
```bash
PROGRESS: 20.90%
Finished files: 20 from 108
Total volume: 6718MB
Remaining files size: 5314MB
```
Another example:
```bash
user@laptop:~$ ./MyProgress.jar 0
```
output:
```bash
PROGRESS: 0.00%  
Finished files: 0 from 205  
Total volume: 11486MB  
Remaining files size: 11486MB
```