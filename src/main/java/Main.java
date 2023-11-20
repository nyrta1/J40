import db.DatabaseConnectionSingleton;
import db.adapter.Converter;
import db.adapter.SqlExecution;
import db.adapter.TxtReaderAdapter;
import factories.CourseElement;
import factories.CourseElementFactory;
import models.BankCard;
import models.Users;
import notifyer.J40NotificationSystem;
import notifyer.SubscribedStudents;
import payment.PaymentStrategy;
import payment.payment_types.HalykBank;
import payment.payment_types.JusanBank;
import payment.payment_types.KaspiBank;
import privacyguard.EncryptStringProvider;
import privacyguard.PlainStringProvider;
import privacyguard.StringProvider;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static Users currentUser;
    private static final J40NotificationSystem notify = new J40NotificationSystem();
    private static String news;
    public static void main(String[] args) {
        // Initializing the db
        DatabaseConnectionSingleton.getInstance();
        loginOrRegister();
    }

    private static void loginOrRegister(){
        System.out.println("**********************************************");
        System.out.println("***     JUZ40 Online Learning Platform     ***");
        System.out.println("**********************************************");
        System.out.println("\n\n");
        System.out.println("Login or Register: \n1. Login \n2. Register");
        int loginOrRegisterChoice = scanner.nextInt();
        switch (loginOrRegisterChoice) {
            case 1 -> {
                // Login page
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();

                // encrypting the password to check from the db
                StringProvider encryptedValue = new EncryptStringProvider(new PlainStringProvider(password));
                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                Object[] result = converterStringToSQLCode.converterTxtToSql("existUser", new Object[]{username, encryptedValue.getStringValue()}, "users");
                if (!(Boolean) result[0]){
                    System.err.println("Login or password is incorrect!");
                    loginOrRegister();
                }
                // If the login and password are correct then user will go to a new page for students or teachers or admin
                currentUser = new Users(username);

                Object[] userRoleObject = converterStringToSQLCode.converterTxtToSql("getUserRole", new Object[]{username}, "userrolemapping");
                String[] userRoleArray = (String[]) userRoleObject[0];
                String userRole = userRoleArray.length > 0 ? userRoleArray[0] : "";

                new SubscribedStudents(currentUser, notify);
                notify.setNews(news);

                switch (userRole) {
                    case "ADMIN" -> {
                        System.out.println("Entered as a admin.");
                        adminPage();
                    }
                    case "TEACHER" -> {
                        System.out.println("Entered as a teacher.");
                        teacherPage();
                    }
                    case "STUDENT" -> {
                        System.out.println("Entered as a student.");
                        studentPage();
                    }
                    default -> System.err.println("Invalid role!");
                }
            }
            case 2 -> {
                // Register page
                System.out.println("Your name: ");
                String userName = scanner.next();
                System.out.println("Your surname: ");
                String userSurname = scanner.next();
                System.out.println("Your future username: ");
                String userUsername = scanner.next();
                System.out.println("Your password: ");
                String firstPassword = scanner.next();
                System.out.println("Enter password again: ");
                String secondPassword = scanner.next();

                if (!firstPassword.equals(secondPassword)) {
                    System.err.println("These passwords are not the same.");
                    loginOrRegister();
                }

                String encryptStringPassword = new EncryptStringProvider(new PlainStringProvider(firstPassword)).getStringValue();

                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("insert", new Object[]{userName, userSurname, userUsername, encryptStringPassword}, "users");
                converterStringToSQLCode.converterTxtToSql("insert", new Object[]{userUsername, 3}, "userrolemapping");
                currentUser = new Users(userUsername);

                System.out.println("Congrats! You've registered successfully. Welcome to the JUZ40 Platform!");
                studentPage();
            }
            default -> System.err.println("Invalid choice");
        }
    }

    private static void studentPage() {
        System.out.println("1. Show all subjects \n2. Show subject content \n3. Subscribe to a subject \n4. My subscribed subjects \n0. Log out");
        switch (scanner.nextInt()) {
            case 1 -> {
                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("select", new Object[]{}, "subject");
                studentPage();
            }
            case 2 -> {
                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("select", new Object[]{}, "subject");

                System.out.println("What subject content do you want to see? Enter subject name: ");
                String subjectName = scanner.next();

                converterStringToSQLCode.converterTxtToSql("contentOfSubject", new Object[]{subjectName}, "lesson");
                studentPage();
            }
            case 3 -> {
                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                Object[] request = converterStringToSQLCode.converterTxtToSql("bankCardExists", new Object[]{currentUser.getUsername()}, "bankcard");
                if (!(Boolean) request[0]){
                    System.out.println("Enter the bank name: \n1.KaspiBank \n2.JusanBank \n3.HalykBank");
                    String bankName = scanner.next();

                    switch (bankName) {
                        case "KaspiBank" -> {
                            PaymentStrategy payment = new KaspiBank();
                            payment.pay();
                        }
                        case "JusanBank" -> {
                            PaymentStrategy payment = new JusanBank();
                            payment.pay();
                        }
                        case "HalykBank" -> {
                            PaymentStrategy payment = new HalykBank();
                            payment.pay();
                        }
                        default -> System.out.println("Invalid choice");
                    }

                    System.out.println("Enter your 16th bank card number:");
                    String bankCardNumbers = scanner.next();
                    System.out.println("Expires: ");
                    String expire = scanner.next();
                    System.out.println("CVV:");
                    String cvv = scanner.next();

                    if (bankCardNumbers.length() == 16 && cvv.length() == 3){
                        BankCard bankCard = new BankCard(currentUser.getUsername(), bankCardNumbers, cvv, expire, bankName);
                        converterStringToSQLCode.converterTxtToSql("insert", new Object[]{bankCard.getUsername(), bankCard.getCardNumber(), bankCard.getCvv(), bankCard.getExpiryDate(), bankCard.getBankName()}, "bankcard");
                    }
                }

                converterStringToSQLCode.converterTxtToSql("select", new Object[]{}, "subject");
                System.out.print("Enter the name of subject that you want to buy: ");
                String buying = scanner.next();
                converterStringToSQLCode.converterTxtToSql("insert", new Object[]{currentUser.getUsername(), buying}, "boughtsubject");
                System.out.println("Congrats! You've bought the subject: " + buying);
                studentPage();
            }
            case 4 -> {
                System.out.println("My subscribed subjects: ");
                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("select", new Object[]{currentUser.getUsername()}, "boughtsubject");
                studentPage();
            }
            case 0 -> {
                currentUser = null;
                loginOrRegister();
            }
            default -> System.err.println("Invalid choice");
        }
    }

    private static void teacherPage() {
        System.out.println("1. Create a course \n2. Create a lesson \n0. Log out");
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println("Enter a subject name:");
                String subjectName = scanner.next();
                System.out.println("Enter the price of subject: ");
                Long priceOfSubject = scanner.nextLong();
                CourseElementFactory factory = new CourseElementFactory();
                CourseElement subject = factory.createSubject(subjectName, priceOfSubject);
                subject.display();

                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("insert", new Object[]{currentUser.getUsername(), subjectName, priceOfSubject}, "subject");
                teacherPage();
            }
            case 2 -> {
                System.out.println("Enter a lesson's subject name: ");
                String belongsSubjectName = scanner.next();
                System.out.println("Enter a lesson name: ");
                String lessonName = scanner.next();
                CourseElementFactory factory = new CourseElementFactory();
                CourseElement lesson = factory.createLesson(belongsSubjectName, lessonName);
                lesson.display();

                Converter converterStringToSQLCode = new TxtReaderAdapter(new SqlExecution());
                converterStringToSQLCode.converterTxtToSql("insert", new Object[]{belongsSubjectName, lessonName}, "lesson");
                teacherPage();
            }
            case 0 -> {
                currentUser = null;
                loginOrRegister();
            }
            default -> System.err.println("Invalid choice!");
        }
    }

    private static void adminPage() {
        System.out.println("1. Set news \n0. Log out");
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println("Enter the news: ");
                news = scanner.next();
                notify.setNews(news);
                // "Only today there will be a discount of the whole subjects -15%!!!"
                adminPage();
            }
            case 0 -> {
                currentUser = null;
                loginOrRegister();
            }
            default -> System.out.println("Invalid choice");
        }
    }
}