# oibsip_task4
# Online Examination System

## Overview

The Online Examination System is a console-based application that allows users to log in, update their profile, change their password, take multiple-choice exams with a timer, and logout. This system demonstrates basic user authentication, profile management, and timed examinations in a simple Java application.

## Features

1. **Login**
   - Users can log in using their user ID and password.
   
2. **Update Profile**
   - Logged-in users can update their profile information.
   
3. **Change Password**
   - Users can change their password for increased security.
   
4. **Take Exam**
   - Users can take a multiple-choice exam with a timer.
   - The exam is automatically submitted when the time is up.
   
5. **Logout**
   - Users can safely log out of the system.

## Classes

### `User`

- **Attributes**:
  - `userId`: String - The user's ID.
  - `password`: String - The user's password.
  - `profileInfo`: String - The user's profile information.

- **Methods**:
  - `User(String userId, String password)`: Constructor to initialize a user.
  - `String getUserId()`: Returns the user ID.
  - `boolean validatePassword(String password)`: Validates the user's password.
  - `void updateProfile(String newProfileInfo)`: Updates the user's profile information.
  - `void updatePassword(String newPassword)`: Updates the user's password.
  - `String getProfileInfo()`: Returns the user's profile information.

### `Question`

- **Attributes**:
  - `questionText`: String - The question text.
  - `options`: String[] - The multiple-choice options.
  - `correctAnswerIndex`: int - The index of the correct answer.

- **Methods**:
  - `Question(String questionText, String[] options, int correctAnswerIndex)`: Constructor to initialize a question.
  - `String getQuestionText()`: Returns the question text.
  - `String[] getOptions()`: Returns the multiple-choice options.
  - `int getCorrectAnswerIndex()`: Returns the index of the correct answer.

### `Exam`

- **Attributes**:
  - `questions`: List<Question> - The list of questions in the exam.
  - `selectedAnswers`: int[] - The answers selected by the user.
  - `duration`: long - The duration of the exam in milliseconds.
  - `submitted`: boolean - Indicates whether the exam has been submitted.

- **Methods**:
  - `Exam(List<Question> questions, long duration)`: Constructor to initialize an exam.
  - `void start()`: Starts the exam.
  - `void submit()`: Submits the exam.

### `OnlineExamSystem`

- **Attributes**:
  - `scanner`: Scanner - The scanner for user input.
  - `users`: List<User> - The list of users.
  - `currentUser`: User - The currently logged-in user.

- **Methods**:
  - `void main(String[] args)`: The main method to run the application.
  - `void setupInitialData()`: Sets up initial user data.
  - `boolean authenticateUser()`: Authenticates the user.
  - `void updateProfile()`: Updates the user's profile information.
  - `void changePassword()`: Changes the user's password.
  - `void takeExam()`: Allows the user to take an exam.

## How to Run

1. Clone or download the project to your local machine.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Compile and run the `OnlineExamSystem` class.
4. Follow the on-screen prompts to log in, update your profile, change your password, take exams, and logout.

## Initial Users

The system comes with two pre-configured users:

1. User ID: `user1`
   Password: `password1`

2. User ID: `user2`
   Password: `password2`

## Example Usage

