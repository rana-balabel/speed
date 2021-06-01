/* Name: Rana Balabel
  Date: May 23 2021

 Program Description: 
 This program is a speed typing game that tests the user's accuracy in typing, as well as their speed. There are three levels to go through: easy,medium, and hard. The user can only advance to the next level after completing three questions at their current level. They may type 0 at any question to quit the level. The program computes their average wpm at the end of the game, and categorizes them based on their speed (very slow, slow, average, fast, and very fast).
 The program terminates completely only at the main menu.

 */


//Importing scanner, TimeUnit, and LocalTime classes
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;

class Main {
  //*throws InterruptedException prevents runtime errors with the TimeUnit class
	public static void main(String[] args) throws InterruptedException {
  Scanner in = new Scanner(System.in);                      //Making a new scanner object
  String userInput;                                         //Temporary variable to hold user input
  int choice, wpmAverage;                                   //Declaring variables for user's choice at menu and final wpm
  System.out.print("\033[H\033[2J");                        //Clears screen
  System.out.flush();

  //Setting up the menu and user input
	while(true)
  {
    System.out.println("\n\t\t\t\t\t\t\t\t\u001b[36;1m💕  HELLO WORLD 💕\u001b[36;1m");
    System.out.println("\n\n\t\t\u001b[33;1mThis is the main menu for the speed typing game! \n\t\tHere are your options:\n\u001b[33;1m");
    while(true)
    {
    System.out.println("\t\t\u001b[36;1m1: Start playing\n\t\t0: Quit Program\u001b[36;1m\n");
    userInput = in.nextLine();                        
      try
      {
        choice = Integer.parseInt(userInput);                                                   //Check for valid input 
        if(choice == 0|| choice == 1)
          break;                                                                                //If input is valid, break out of valid input loop
        else
          System.out.println("\u001b[33;1mNot an option 😟. Your options are:\n\u001b[33;1m");
      }
      //If user's input is invalid, force the user to enter an acceptable value
      catch (Exception e)
      {
        System.out.println("\u001b[33;1mThat's not a valid input 👎! Your options are:\n\u001b[33;1m");
      }
    }

    if (choice == 1)                                                                            //If user chooses to start playing
    {
      System.out.print("\033[H\033[2J");                                                        //Clears screen
      System.out.flush();
      TimeUnit.SECONDS.sleep(1);                                                                //Pause for one second
      wpmAverage = SpeedTypingGame(in);                                                         //Call on game method
      exitScreen(wpmAverage, in);                                                               //Output final wpm average and speed category
      System.out.print("\033[H\033[2J");                                                        //clears screen
      System.out.flush();
    }
    else if (choice == 0)                                                                       //If user chooses to quit
    {
      System.out.println("\u001b[36;1m0:  It was fun having you!  ");                           //Print end message
      System.out.println("★─▄█▀▀║░▄█▀▄║▄█▀▄║██▀▄║─★");
      System.out.println("★─██║▀█║██║█║██║█║██║█║─★");
      System.out.println("★─▀███▀║▀██▀║▀██▀║███▀║─★");
      System.out.println("★───────────────────────★");
      System.out.println("★───▐█▀▄─ ▀▄─▄▀ █▀▀──█───★");
      System.out.println("★───▐█▀▀▄ ──█── █▀▀──▀───★");
      System.out.println("★───▐█▄▄▀ ──▀── ▀▀▀──▄───★\u001b[36;1m0:");
      break;                                                                                    //Quit the program
    }
    
  }
	
	in.close();                                                                                   //Closes the scanner
	}
	
  /* Method: SpeedTypingGame()
  * Goes through levels 1, 2, 3 of the speed typing game. The method records the wpm average after every level completed. 
  * Pre: scanner in must be passed as an argument from main method
  * Post: Returns the wpm average across the levels the user has completed
  */
  public static int SpeedTypingGame(Scanner in) throws InterruptedException 
  {
    //Variable declarations of all three levels' wpm averages,  and the final wpm average across all the levels completed
    int wpmTotal, level1, level2, level3, wpmAverage;
    Introduction(in);                                      //Prints introductory messages
  
    level1 = easyQuestions(in);                            //Calls on level 1 and stores the user's average wpm in level 1
    TimeUnit.SECONDS.sleep(2);                                       
    System.out.print("\033[H\033[2J");                     //Clears screen
    System.out.flush(); 
    if(level1<0)        //If the user quits during level 1, their wpm average is subtracted by 1000 to distinguish that they quit mid-way through playing 
    {
      wpmTotal = level1+1000;                              //return the value of the wpm average back to its original value
      return wpmTotal;                                     //return the value of wpm to the exit screen 
    }
    wpmTotal = level1;                                     //add the average wpm of level 1 to the total wpm value
    TimeUnit.SECONDS.sleep(2);                      

    level2 = mediumQuestions(in);                          //once level 1 is completed, call on level 2
    TimeUnit.SECONDS.sleep(2);
    System.out.print("\033[H\033[2J");                     
    System.out.flush();
    if(level2<0)                                           //If the user quits during level 2
    {
      if(level2+1000 != 0)                                 //If the user doesn't quit before completing their first sentence
      wpmTotal = (wpmTotal + (level2+1000))/2;             //calculate the average wpm across level 1 and 2
      return wpmTotal;                                     //print the average wpm across levels 1 and 2 on exit screen
    }
    wpmTotal = wpmTotal + level2;                          //add the total wpm across levels 1 and 2
    TimeUnit.SECONDS.sleep(2);

    level3 = hardQuestions(in);                            //call on level 3 to store the user's wpm average in level 3
    TimeUnit.SECONDS.sleep(2);
    System.out.print("\033[H\033[2J");                     
    System.out.flush();
     if(level3<0)                                          //if the user quits during level 3
    {
      if(level3+1000 != 0)                                 //if they quit after typing at least one sentence
      wpmTotal = (wpmTotal + (level3+1000))/3;             //calculate the average wpm across levels 1 2 and 3
      return wpmTotal;                                     //return the wpm average to exit screen
    }

    wpmTotal = wpmTotal + level3 ;	                       //add the total wpm across all three levels
    wpmAverage = wpmTotal / 3;                             //divide the total by the 3 levels to find average

    return wpmAverage;                                     //return overall average of all three levels completed to exit screen
  } 

/* Method: exitScreen()
  * Prints the final wpm average computed and categorizes the user's speed 
  * Pre: wpm average computed in the speedTypingGame method must be passed as an argument 
  * Post: prints the statements onto the screen
*/
  public static void exitScreen(int wpmAverage, Scanner in) throws InterruptedException
  {
    System.out.println("\n\n\n\n\n\n\n\n\n\u001b[33;1m*********************************************************************");
    System.out.println("             !! YOU COMPLETED THE SPEED TYPING GAME !!\u001b[33;1m");
    System.out.println("\u001b[36;1mYour average words per minute across the levels you completed is: "+wpmAverage);
    if(wpmAverage <= 26)
      System.out.println("Your category is: \u001b[33;1mVery Slow.\u001b[33;1m\n\u001b[36;1mKeep playing this game to get better though! 🤐");
    else if (wpmAverage > 26 && wpmAverage <= 45)
      System.out.println("Your category is: \u001b[33;1mSlow.\u001b[33;1m\n\u001b[36;1mYou're not bad!\nBut there's definitely room for improvement 🙂");
    else if (wpmAverage> 45 && wpmAverage<=65)
      System.out.println("Your category is: \u001b[33;1mAverage.\u001b[33;1m \n\u001b[36;1mGreat job!\nYou're on par with the rest of the population's typing speed 😆");
    else if (wpmAverage> 65 && wpmAverage<95)
      System.out.println("Your category is: \u001b[33;1mFast.\n\u001b[33;1m\u001b[36;1mWow. Legendary behavior 😎\nConsider a career in data entry! ⌨");
    else if (wpmAverage>=95)
      System.out.println("Your category is: \u001b[33;1mVERY fast.\u001b[33;1m\n\u001b[36;1mYou might be an undercover robot.🤖");
    System.out.println("\n\u001b[33;1m*********************************************************************\u001b[33;1m");
    TimeUnit.SECONDS.sleep(1);
    System.out.println("\u001b[36;1m\tPress enter to go back to the main menu 😋");
    in.nextLine();
  }

  /* Method: Introduction()
  * Prints the game's instructions and explains the game for the user
  * Pre: scanner must be passed as an argument from the main method
  * Post: prints the statements onto the screen
  */
	public static void Introduction(Scanner in) throws InterruptedException
	{
		//introduction section
		System.out.println("\u001b[33;1m*********************************************************************\n");
		System.out.println("              WELCOME   TO    THE     SPEED     TYPING    GAME   \n");
		System.out.println("*********************************************************************\n\n\u001b[33;1m");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\u001b[36;1mThere are three levels for you to go through.\nWe will start with easy, then escalate to medium, then hard.\n"); 
		TimeUnit.SECONDS.sleep(4);
		System.out.println("You won't be allowed to advance to the next level without \nsuccessfully completing three questions at your current level!\n");
		TimeUnit.SECONDS.sleep(4);
		System.out.println("Once you've successfully completed a question, your Words Per Minute will be printed before moving onto the next challenge.\n");
		TimeUnit.SECONDS.sleep(4);
		System.out.println("Finally, when all levels are complete, you will be prompted back to the main menu.\n\nAlternatively, you can type '0' during any question to quit the level you're at! \u001b[36;1m\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n                    \u001b[33;1m \u2665 PRESS THE ENTER KEY TO START \u2665 \u001b[33;1m               ");
		in.nextLine();
		System.out.print("\033[H\033[2J");                                                              
    System.out.flush();
	}

	/* Method: easyQuestions()
   Goes through the first level of the speed typing game. Records wpm average across all questions completed. User is allowed to  quit by  entering 0 during a question
   Pre: scanner must be passed as an argument from the main method
   Post: returns wpm average of level 1 to speedTypingGame method
  */
	public static int easyQuestions(Scanner in) throws InterruptedException
	{
    //Variable declarations of the correct answers' counter, user's choice, wpm, and the average wpm in this level
		int correct = 0, choice, wpm = 0, wpmAverage = 0; 
		String choiceInput;                               //temporary variable to hold choice input, to be used for valid input check 
		double start, end;                                //variables record the start and end of time increments 
		int i [] = new int [4];                           //array of random numbers to be used to randomize sentences
		int counter = 0;                                  //counts the number of times user has typed a sentence
		String userInput;                                 //variable to hold user's inputed sentence
		int arrayCounter = -1;                            //controls which index array to use during a question from the random number array

    //array to hold all the sentences for level 1
		String easyQs [] = {"Red is the color of my true love's hair. Green is the color of my true love's socks", "A crocodile cannot stick out its tongue, but a cat has thirty two muscles in each ear", "A 'jiffy' is an actual unit of time for 1/100th of a second. Bet you didn't know that.", "I did the Macarena with my brother while listening to Gangnam Style because the voices told me to.", "Did you know that the human body gives off light, and that ketchup was once sold as medicine?"};
		
		
		System.out.println("\u001b[36;1m\n\nTo pass the easy level, YOU MUST correctly type the following sentences EXACTLY as you see them. Good luck! 💪 \u001b[36;1m\n");
		i = randomNumberGenerator();                    //generate random numbers and store them in the array
    TimeUnit.SECONDS.sleep(3);
		while(correct < 3)                              //while the user does not have 3 correct answers
    {
			arrayCounter++;                               //add one to the control variable
			if (arrayCounter>4)                           //to prevent arrayIndexOutOfBound errors, reset the value of the counter when                                                  its more than 4 (this runs when user types more than 2 answers incorrectly)
			{
				arrayCounter = 1;
				i = randomNumberGenerator();                //generate new order of randomized sentences 
			}
      TimeUnit.SECONDS.sleep(3);
			System.out.println("\u001b[32;1m"+easyQs[i[arrayCounter]]+"\u001b[32;1m"); //prints random sentence from easyQs array
			start =  LocalTime.now().toNanoOfDay();                                    //starts counting local time in nanoseconds
			userInput = in.nextLine();                                                 //user starts typing and finishes
			end = LocalTime.now().toNanoOfDay();                                       //stops counting local time 
			userInput = userInput.trim();                                              //trims user input for any leading or trailing spaces
			wpm = wpmCalculator(start, end, userInput);                                //calculate the wpm using the time elapsed and user input
			wpmAverage = wpmAverage + wpm;                                             //add it to the total wpm in this level

			if (userInput.equals("0"))                                                  // if user chooses to quit
			{
				System.out.println("\n\nLoading exit screen...😊");
				if (counter == 0)                                                         //if they quit before trying a sentence
				{
					wpmAverage = 0;                                                         //set their wpm average to be 0
					counter=1;                                                              //to prevent divide by zero error, set counter = 1
				}
				return (wpmAverage/counter)-1000;  //subtract their wpm average by 1000 so the speedTypingGame method can recognize that the user has chosen to quit
			}

			System.out.println("\n\u001b[36;1mYour estimated words per minute are "+wpm+ "!!");
			counter++;
			if(userInput.equals(easyQs[i[arrayCounter]]))                           //if it was typed correctly
			{
				correct++;                                                            //increment correct by 1
        System.out.println("Amazing! ✅\n");
				if (correct == 3)                                                     //once theyve gotten 3 correct, give them the choice of quitting or advancing 
				{
					while (true) 
					{
						System.out.println("You got 3 correct! Would you like to advance to the medium level or quit? \n\nEnter 0 to quit, or 1 to advance to the next level.");
						choiceInput = in.nextLine();                                      //checks for valid input
						try
						{
							choice = Integer.parseInt(choiceInput);
							if(choice == 1 || choice == 0)
							{
								break;
							}
							else
							{
							 System.out.println("That's not a valid choice..Choose either 0 or 1 please 🥴");
							}
						}
            //forces the user to enter an integer valur of 0 or 1 
						catch (Exception e)
						{
							System.out.println("That's not a valid choice..Choose either 0 or 1 please 🥴");
						}
					}
					if (choice == 0)                                                //if they choose to quit
					{
						System.out.println("\n\nLoading exit screen...😊");
						return (wpmAverage/counter)-1000;                             //return to speedTypingGame method 
					}
				}
			}
			else                                                                  //if they typed it incorrectly
			{
				System.out.println("You messed up somewhere. Try again 😢\n");
				TimeUnit.SECONDS.sleep(1);                                          
			}
    }
		System.out.println("\u001b[33;1mWOAH! Great work. Loading level 2... 👀👀\u001b[33;1m");
		return wpmAverage/counter;                                             //returns overall average wpm for level 1
  }

/* Method: mediumQuestions()
  * Goes through the second level of the speed typing game. Records wpm average across all questions completed. User is allowed to  * quit by entering 0 whenever
  * Pre: scanner must be passed as an argument from the main method
  * Post: returns wpm average of level 2 to speedTypingGame method
  */
	public static int mediumQuestions(Scanner in) throws InterruptedException
	{
		//Variable declarations of the correct answers' counter, user's choice, wpm, and the average wpm in this level
		int correct = 0, choice, wpm = 0, wpmAverage = 0; 
		String choiceInput;                               //temporary variable to hold choice input, to be used for valid input check 
		double start, end;                                //variables record the start and end of time increments 
		int i [] = new int [4];                           //array of random numbers to be used to randomize sentences
		int counter = 0;                                  //counts the number of times user has typed a sentence
		String userInput;                                 //variable to hold user's inputed sentence
		int arrayCounter = -1;                            //controls which index array to use during a question from the random number array

    //array of medium questions for level 2
		String mediumQs [] = {"One morning, I shot an elephant in my pajamas. How he got into my pajamas I'll NEVER know", "The complex houses married and single soldiers and their families", "Louis Tomlinson, Harry Styles, Liam Payne, Zayn Malik, and Niall Horan need to get back together.", "The useless designer quickly threw because some bee accidentally breathed into a hot painter which, became a useless, territorial spider.", "The slimy lawyer angrily breathed because some elephant tediously ran down a hot photographer which, became a chubby, dazzling engineer."};
		
		System.out.println("\u001b[36;1m\n\nTo pass the medium level, YOU MUST correctly type the following sentences EXACTLY as you see them. Are you really a typing pro? 💪 \n\u001b[36;1m");
		TimeUnit.SECONDS.sleep(3);
		i = randomNumberGenerator();                    //generate random numbers and store them in the array
		while(correct < 3)                              //while the user does not have 3 correct answers
    {
			arrayCounter++;                               //add one to the control variable
			if (arrayCounter>4)                           //to prevent arrayIndexOutOfBound errors, reset the value of the counter when                                                  its more than 4 (this runs when user types more than 2 answers incorrectly)
			{
				arrayCounter = 1;
				i = randomNumberGenerator();                //generate new order of randomized sentences 
			}

      TimeUnit.SECONDS.sleep(3);
			System.out.println("\u001b[32;1m"+mediumQs[i[arrayCounter]]+"\u001b[32;1m"); //prints random sentence from easyQs array
			start =  LocalTime.now().toNanoOfDay();                                      //starts counting local time in nanoseconds
			userInput = in.nextLine();                                                   //user starts typing and finishes
			end = LocalTime.now().toNanoOfDay();                                         //stops counting local time 
			userInput = userInput.trim();                                                //trims user input for any leading or trailing spaces
			wpm = wpmCalculator(start, end, userInput);                                  //calculate the wpm using the time elapsed and user input
			wpmAverage = wpmAverage + wpm;                                               //add it to the total wpm in this level

			if (userInput.equals("0"))                                                   // if user chooses to quit
			{
				System.out.println("\n\nLoading exit screen...😊");
				if (counter == 0)                                                         //if they quit before trying a sentence
				{
					wpmAverage = 0;                                                         //set their wpm average to be 0
					counter=1;                                                              //to prevent divide by zero error, set counter = 1
				}
				return (wpmAverage/counter)-1000;  //subtract their wpm average by 1000 so the speedTypingGame method can recognize that the user has chosen to quit
			}

			System.out.println("\n\u001b[36;1mYour estimated words per minute are "+wpm+ "!!");
			counter++;
			if(userInput.equals(mediumQs[i[arrayCounter]]))                           //if it was typed correctly
			{
				correct++;                                                            //increment correct by 1
        System.out.println("Great! ✅\n");
				if (correct == 3)                                                     //once theyve gotten 3 correct, give them the choice of quitting or advancing 
				{
					while (true) 
					{
						System.out.println("You got 3 correct! Would you like to advance to the HARD level or quit? \n\nEnter 0 to quit, or 1 to advance to the next level.");
						choiceInput = in.nextLine();                                      //checks for valid input
						try
						{
							choice = Integer.parseInt(choiceInput);
							if(choice == 1 || choice == 0)
							{
								break;
							}
							else
							{
							 System.out.println("That's not a valid choice..Choose either 0 or 1 please 🥴");
							}
						}
            //forces the user to enter an integer valur of 0 or 1 
						catch (Exception e)
						{
							System.out.println("That's not a valid choice..Choose either 0 or 1 please 🥴");
						}
					}
					if (choice == 0)                                                //if they choose to quit
					{
						System.out.println("\n\nLoading exit screen...😊");
						return (wpmAverage/counter)-1000;                             //return to speedTypingGame method 
					}
				}
			}
			else                                                                  //if they typed it incorrectly
			{
				System.out.println("You messed up somewhere. Try again 😢\n");
				TimeUnit.SECONDS.sleep(1);                                          
			}
    }
		
		System.out.println("\u001b[33;1m🎊  MISSION ACCOMPLISHED 🎊\nLoading level 3... 👀👀\u001b[33;1m");
		return wpmAverage/counter;                                              //returns overall average wpm of level 2
	}
	
  /* Method: hardQuestions()
  * Goes through the third level of the speed typing game. Records wpm average across all questions completed. User is allowed to  * quit by entering 0 whenever
  * Pre: scanner must be passed as an argument from the main method
  * Post: returns wpm average of level 3 to speedTypingGame method
  */
	public static int hardQuestions(Scanner in) throws InterruptedException
	{
	  //Variable declarations of the correct answers' counter, wpm, and the average wpm in this level
		int correct = 0, wpm = 0, wpmAverage = 0; 
		double start, end;                                //variables record the start and end of time increments 
		int i [] = new int [4];                           //array of random numbers to be used to randomize sentences
		int counter = 0;                                  //counts the number of times user has typed a sentence
		String userInput;                                 //variable to hold user's inputed sentence
		int arrayCounter = -1;                            //controls which index array to use during a question from the random number array

    //array of hard questions for level 3
		String hardQs [] = {"When I went to the suburban part of Piazzas, Italy, I assumed I would find these pizzaz pizzas. Alas, all I found was obstinance which made my foramen close up.","Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo buffalo.","There is nO way you can type this unmanageable, problematical, unaccomodating, troublesome, perplexing, PUZZLING sentence", "The fancy author evenly designed because some model only designed anti a dumb policeman which, became a maniacal, maniacal elephant.", "A rough-coated, dough-faced, thoughtful ploughman strode through the streets of Scarborough; after falling into a slough, he coughed and hiccoughed"};
		
		
		System.out.println("\n\n\u001b[36;1mTo pass the HARD level, YOU MUST correctly type the following sentences EXACTLY as you see them.\n Remember to pay close attention to detail! 💪 \n\u001b[36;1m");
		TimeUnit.SECONDS.sleep(3);
		i = randomNumberGenerator();                      //generate random numbers and store them in the array
		while(correct < 3)                                //while the user does not have 3 correct answers
		{
		  arrayCounter++;                                 //add one to the control variable
			if (arrayCounter>4)                             //to prevent arrayIndexOutOfBound errors, reset the value of the counter when                                                  its more than 4 (this runs when user types more than 2 answers incorrectly)
			{
				arrayCounter = 1;
				i = randomNumberGenerator();                  //generate new order of randomized sentences 
			}

      TimeUnit.SECONDS.sleep(3);
			System.out.println("\u001b[32;1m"+hardQs[i[arrayCounter]]+"\u001b[32;1m"); //prints random sentence from easyQs array
			start =  LocalTime.now().toNanoOfDay();                                     //starts counting local time in nanoseconds
			userInput = in.nextLine();                                                  //user starts typing and finishes
			end = LocalTime.now().toNanoOfDay();                                        //stops counting local time 
			userInput = userInput.trim();                                               //trims user input for any leading or trailing spaces
			wpm = wpmCalculator(start, end, userInput);                                 //calculate the wpm using the time elapsed and user input
			wpmAverage = wpmAverage + wpm;                                              //add it to the total wpm in this level
			
			if (userInput.equals("0"))                                                  //if user chooses to quit
			{
				System.out.println("\n\nLoading exit screen...😊");
				if (counter == 0)                                                         //if they quit before trying a sentence
				{
					wpmAverage = 0;                                                         //set their wpm average to be 0
					counter=1;                                                              //to prevent divide by zero error, set counter = 1
				}
				return (wpmAverage/counter)-1000;  //subtract their wpm average by 1000 so the speedTypingGame method can recognize that the user has chosen to quit
			}

			System.out.println("\n\u001b[36;1mYour estimated words per minute are "+wpm+ "!!");
			counter++;
			if(userInput.equals(hardQs[i[arrayCounter]]))                               //if they type it correctly
			{
				correct++;                                                                //add one to the value of correct answers
        System.out.println("Awesome! ✅\n");
				if (correct == 3)                                                        
				{
					System.out.println("Congratulations! You got 3 correct 😁\n");
				}
			}
			else                                                                       //if answer is typed incorrectly
			{
				System.out.println("You messed up somewhere. Try again 😢\n");             
				TimeUnit.SECONDS.sleep(1);                                               
			}
		}
		
		System.out.println("\u001b[33;1mGreat work! You're literally a typing expert now\n\nLoading end screen.. 👀👀🙉\u001b[33;1m");
		TimeUnit.SECONDS.sleep(2);
		return wpmAverage/counter;                                                   //returns overall wpm average of level 3
	}
	
  /* Method: wpmCalculator()
  * Calculate the words per minute of a given user input
  * Pre: values of start and end of local time in nanoseconds, and a string of user input must be passed as arguments
  * Post: returns words per minute of the user input
  */
	public static int wpmCalculator(double start, double end, String userInput)
	{
		double timeElapsed = (end - start)/1000000000.0 ;     //calculates the time elapsed then converts it to seconds from nanoseconds
		int numChars = userInput.length();                    //counts number of characters of given input
		int wpm = (int) ((((double) numChars / 5) / timeElapsed) * 60); //uses wpm formula to calculate user's words per minute and typecasts it to an integer value. 
		return wpm;                                             //returns calculated value of wpm 
	}

 /* Method: randomNumberGenerator()
  * Generates an array of random, non-repeating, integers to be used for randomized sentences
  * Pre: none
  * Post: returns array of random numbers
  */
  public static int[] randomNumberGenerator()
  {
    //variable declarations and initializations
    int randomNum = 0;    
    int randomArray [] = {-1,-1,-1,-1,-1};      //initializing all index array values to be -1

    for(int i = 0; i<5; i++)                    //loops 5 times to create 5 random, nonrepeating integers
    {
      randomNum = (int) (Math.random()*5);      //generates a number between 0 to 4
      
      if(randomNum == randomArray[0] || randomNum == randomArray[1] || randomNum == randomArray[2] || randomNum == randomArray[3] || randomNum == randomArray[4])             //if the number has already occured
      {
        i--;                                    //repeat the loop
      }
      else 
      {
        randomArray[i] = randomNum;             //if it's unique, store it in one of the indices
      }

    }
  return randomArray;                           //return the array of random integers                    
  }

}
