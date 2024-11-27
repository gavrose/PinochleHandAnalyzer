# PinochleHandAnalyzer
# Enter a hand of Pinochle Cards and find the meld in that hand.



# This project heavily uses HashMaps. We briefly touched on HashMaps in class, but I wanted to understand them better and how to use them. This project boosted that understanding and has practical applications.

# I created this project because I frequently play pinochle with my family, and sometimes people struggle to analyze their hand quickly and efficiently. This program will assist in analyzing the hand by finding all possible meld combinations.

# NOTICE - This program simply finds all possible meld combinations it does not score them. I hope to add this feature in the future.

# This program allows the user to enter pinochle cards (A, K, Q, J, 10, 9) to form a hand. It will then analyze the given hand based on 7 criteria:
 **Pinochle** - Jack of Diamonds and Queen of Spades                                                                                               
 **Marriage** - A king and queen in the same suit                                                                                                  
 **Run** - A, 10, K, Q, J in the same suit                                                                                                      
 **Round of Aces** - An ace in all 4 suits                                                                                                         
 **Round of Kings** - A king in all 4 suits                                                                                                        
 **Round of Queens** - A queen in all 4 suits                                                                                                      
 **Round of Jacks** - A jack in all 4 suits                                                                                              
 
# Hands can have multiple of one thing, however, it must satisfy the base condition again in order to count.
Example 1 - 3 jacks of diamonds and 2 queens of spaids = 2 pinochles                                                                              
Example 2 - 3 kings of hearts and 1 queens of hearts = 1 marriage in hearts                                                                        
Example 3 - 2 rounds of aces = 2 aces in all 4 suits                        

 # Trump information
 Once all possible meld combinations have been found, the user will be asked what suit trump is. This input can alter their meld due to the complex relationship between runs and marriages.
 When a user scores a run that means they have A 10 K Q J. However, the run can only be scored if it is in the same suit as trump. If the run is scored, the marriage contained within it (A 10 
   **K Q** J) is not scored. However, if the run is not in trump and is not scored, the marriage will be scored.
   
 Example 1 - A 10 K K Q Q J in spades. If trump is spades, the user will score a run and a marriage. If trump is not spades, the user will score 2 marriages.
 
 Example 2 - A 10 K Q J in diamonds. If trump is diamonds, the user will score a run. If trump is not diamonds, the user will score a marriage.
 
 Example 3 - A A 10 10 K K K Q Q Q J J in hearts. If trump is hearts, the user will score a double run and a marriage. If trump is not hearts, the user will score 3 marriages.

# How to compile and run
**Terminal** - Navigate to the folder where the project is stored using terminal cd commands. Enter "javac Main.java" java into the terminal. Enter "java Main" into the terminal.

**IDE** - Open the 'Main.java' file in your desired IDE and run the file.

# How to use 
 When the Main file is run, the user will be asked if they are playing with 9s in the game or not, followed by the size of the hand they want to input. The user can then begin inputting the cards they want to analyze following the format 'rank suit'. Users only need to enter *the first letter of the rank and suit*.                                                                                                                   
 Example input 1 - Ace of Clubs = 'a c'                                                                                                            
 Example input 2 - King of Hearts = 'k h'                                                                                                          
 Example input 3 - Queen of Spades = 'q s'                                                                                                          
 Example input 4 - Jack of Diamonds = 'j d'                                                                                                        
 Example input 5 - 10 of Clubs = '10 c' or 't c'                                                                                                           
 Example input 6 - 9 of Spades = '9 s' or 'n s'  

 # Additional features
 **Undo** - If users input a card incorrectly, they can enter 'undo' and it will remove the last card that was entered. This can be repeated until the list is empty if desired.  
 **Clear** - If users want to remove all of the cards they have previously inputted, they can enter 'clear' when entering a card and the current hand will become empty.

 # Edits 
 - 11/26 - added trump and run/marriage rule calculations
 - 11/27 - added t and n to input 10 and 9 to improve UI
