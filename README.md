# Decryption Program
For this assignment you need to write an application that takes 3 command-line arguments and decrypts a file into another file: 
 
**key** – string key provided                                      
**input** – input file that contains the text to decrypt in text file format                                       
**output** – output file generated by Decrypt 
 
 
An example input and the output it produces: 
 
Original (from https://en.wikipedia.org/wiki/Substitution_cipher	):                                             
In cryptography, a substitution cipher is a method of encoding by which units of plaintext are replaced with ciphertext, according to a fixed system; the "units" may be single letters (the most common), pairs of letters, triplets of letters, mixtures of the above, and so forth. The receiver deciphers the text by performing the inverse substitution. 
 
Encrypted:                            
WGVYXOBJOTOR GOHYOYOJXUCT RJAFKUUSGYJOHBJBOJWVBWNXCBTAOS NVUOYAO 
XYGGGXYWZJLOLYTWOKATO OJJVJTWOABOU Y JAGNROEAZXOWOUAAWETXBYOHJYH 
YAFRGUYAOEAOFJYXONXVAOOVBHOUNSAOSJYOOVAYUKVAOOLOOOOJOLTFGVYYCAVB 
B OOYYJBWOKVAAFOUVYJKVTXFGLOVYEKOOBTLLOVYOOA OOAFJAYOAYOBTYTBTRY 
CHYTOTAAGUTOTAY OOYJJXCTBOXAHVETOGBAAXOOAOKXP ANPVOWAOYKABOAWYOB 
	SPCB    UURJ    AAY     OAVU    JJVY    FOOR    YTYO    XVJ 	 
 
After decryption:                       
IN CRYPTOGRAPHY  A SUBSTITUTION CIPHER IS A METHOD OF ENCODING B 
Y WHICH UNITS OF PLAINTEXT ARE REPLACED WITH CIPHERTEXT  ACCORDI 
NG TO A FIXED SYSTEM  THE  UNITS  MAY BE SINGLE LETTERS  THE MOS 
T COMMON   PAIRS OF LETTERS  TRIPLETS OF LETTERS  MIXTURES OF TH 
E ABOVE  AND SO FORTH  THE RECEIVER DECIPHERS THE TEXT BY PERFOR 
MING THE INVERSE SUBSTITUTION 
 
As you can see, any symbols other than 26 English letters and space are replaced with spaces, and all text is converted to uppercase, for simplicity  
## How to run  
Must run this program in terminal as it takes command line arguments  
java decrypt key input output  
