---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# User Guide

## Streamline Patient Management and Medical Records with Just a Few Taps!

Medbook is a **desktop application** crafted specifically for **doctors** and **medical administrative assistants** of private clinics to manage patient details and medical records.

Medbook provides a streamlined and user-friendly interface that empowers doctors and medical administrative assistants to effortlessly track patient details and medical records. This ensures that critical information is well-organized, easily accessible, and fully secure for your peace of mind.

Here's how Medbook can streamline your tasks:

- Quickly save, edit, and remove patient details.
- Effortlessly handle patient appointments and records.
- Search for specific information in a snap.
- Get a comprehensive view of patient data, medical records, and appointments at a glance.

Discover the power of Medbook:

- **Digitalize Your Clinic**: Effortlessly transition to a digital clinic with our user-friendly features.
- **Optimized for Speed**: Medbook offers both a Command Line Interface (CLI) and an intuitive Graphical User Interface, making it ideal for fast typists like you.
- **QuickStart**: New to Medbook? Click [here](#quick-start-) to start your journey.

<!-- * Table of Contents -->
<page-nav-print />

---

## Table of Contents
<!-- * Table of Contents -->
<page-nav-print />

## How to Navigate the User Guide

Welcome to the Medbook User Guide! Our goal is to empower you with the knowledge and confidence to make the most of Medbook's features.

- **Effortless Navigation**: Use the table of contents for seamless navigation between sections.
- **Quick Start for New Users**: If you're new to Medbook, start with the [Quick Start](#quick-start-) section to get up and running.
- **Explore Features**: For those who have already set up Medbook, dive into the [Features](#features) section to discover all that our application has to offer.

We're here to make your experience as user-friendly as possible. Let's get started!

## Quick start 

1. Ensure you have Java `11` or above installed in your Computer.
   - [How do I check my version of Java?](#faq)
1. Download the latest jar file (`medbook.jar`) from our [github release](https://github.com/AY2324S1-CS2103T-T12-4/tp/releases).

1. Move the jar file to the folder you want to store the data of the patients.

    - Create a folder with your preferred name (you can call it Medbook) where you would like to house the application.
    - Move the downloaded jar file to the folder as shown below.
      - For Mac users:![Ui](images/MacUserDir)
      - For Windows users:![Ui](images/WinUserDir)

1. Run Medbook application

    **On Windows**:
    - Double-click medbook.jar to run the application.
    - If the GUI doesn't show up, try the following operations.
    - **Open Command Prompt**: Search for "Command Prompt" in the Windows search bar and open it.
    - Navigate to the folder where you have stored the jar file using the cd command.
    - Enter java -jar medbook.jar and press Enter. This command will execute the application.<br> 
<box type="info" seamless>
      Please note that for Windows users, you can navigate to the folder where you house medBook. In the file address bar, click on the path to the current folder to highlight it. Type powershell and press enter. This will open a PowerShell window with the current folder as the working directory.
</box>
   
   **On Mac**:
    - **Open Terminal**: You can find Terminal in the "Utilities" folder within the "Applications" folder. Or, you can use Spotlight Search by pressing Command + Spacebar and typing "Terminal."
    - Navigate to the folder where you have stored the jar file using the cd command
    - Run the Application: Enter java -jar medbook.jar and press Enter. This command will execute the application.<br>
<box type="info" seamless>
Please note that for Mac users,  in the Terminal on a Mac, you can also drag and drop the "medbook.jar" file directly into the Terminal window after typing `java -jar` to auto-fill the file path.
</box>

The application should open up similar to the image below.
![Ui](images/MedBookUi.png)
1. Learn more about navigating the GUI [here]().
2. For new users, learn how to use Medbook [here]().
3. For advanced users, view all the available features [here](#features).

## Glossary

### Definitions

Here are some description of the terms used throughout this user guide.

| **Term**  | **Definition**                                                                                                                                                             |
|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Parameter | A parameter allow you to customize the behavior of the command by providing specific values                                                                                |
| Command   | A command is a textual input that users type into the command-line interface to communicate with the application                                                           |
| GUI       | GUI is graphical user interface. It manages the interaction between user and the application and also gives a visual display of Medbook.                                   |
| GUI component| GUI components are the interactive parts of the application that you can see and interact with. For more information of different GUI components. Please refer to [this]() |
| CLI| CLI or Command Line Interface,  is a text-based way to interact with the application.                                                                                      |

## Navigating the Graphical User Interface (GUI)

![Ui Component](images/UiComponent.png)

Here is a brief summary of GUI component of the main window of Medbook.

| **Name of component**    | **Description**                                                                                             |
|--------------------------|-------------------------------------------------------------------------------------------------------------|
| Menu Bar                 | Contains the help and file drop down menu for Medbook                                                       |
| Message Display Box      | Display the message of every command. Either can be an error message or success message                     |
| Patient List             | Contains all the Patient cards stored in Medbook                                                            |
| Patient information card | Contains information of the Patient including age, blood type, gender, contact information and allergies    |
| Patient being viewed     | Contains the patient card of currently being viewed patient                                                 |
| Medical record List      | Contains all the Record card of the patient currently being viewed                                          |
| Record card              | Contains information of the medical record including appointment date, condition, and prescribed medication |
| Pinned Patient List      | Contains all the patient cards after command pin                                                            |
| Command Input Box        | Allow users to input commands                                                                               |


![Appointment Window](images/ViewAppointmentWindow.png)

| ** Name of component | **Description**                                                          |
|----------------------|--------------------------------------------------------------------------|
| Appointment card     | contains information of appointments including name, date and NRIC number |
| Appointment list     | Holds a list of Appointment card                                         |
| Calendar             | A calendar that displays the appointment                                 |

## Medbook Tutorial

1. Launch the Medbook application. You may refer to [here](#quick-start-)
   - Note on launch, Medbook will populate some sample data.

2. Let us try invoking the help window. Type help in the command box and press Enter to execute it. 
Please refer to the first section of [feature](#features) to have a more clear understanding of requirements of the parameters. 
A similar help box as below should pop up.<br> <img src="images/HelpWindow.png" width="300"/>
3. Now, let us try adding a new patient. In the command box, type in `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` and press Enter. A success message should be displayed.<br>
<img src="images/AddPatientSuccess.png" width="500"/>
4. Try a few more patient to Medbook by yourself!
5. Let us view medical records of the first patient. Key in `view 1` to see all the records of the first patient.<br><img src="images/View.png" height="300"/>

You may now have a basic understanding of this software. Try out the features in the [following section](#features) on your own and explore the potential of Medbook!


---

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [al/ALLERGIES]` can be used as `n/John Doe al/Pencillin` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[al/ALLERGIES]…​` can be used as ` ` (i.e. 0 times), `al/Pencillin`, `al/Pollen al/Dust` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- `PATIENTID` refers to the unique patient ID shown in the displayed patient list. The ID **must be a positive integer** 1, 2, 3, …​

</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

### Adding a patient: `addpatient`

Adds a patient's contact and medical details.

Format: `addpatient n/NAME e/EMAIL p/PHONE g/GENDER a/AGE bt/BLOODTYPE [al/ALLERGIES]...`

- Adds a patient with information including: `NAME`, `EMAIL`, `GENDER`, `PHONE`, `AGE`, `BLOODTYPE`, `ALLERGIES`

Constraints

- `NAME`: Up to 256 alphanumeric characters
- `EMAIL`: Valid email format: `email@domain.com`
- `GENDER`: Either `M` or `F`
- `PHONE`: Must be 8 digits
- `AGE`: Must be a positive integer
- `BLOODTYPE`: Must be one of `A-`, `A+`, `B-`, `B+`, `AB-`, `AB+`, `O-`, `O+`
- `ALLERGIES`: Up to 256 alphanumeric characters

Examples:

`addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin`
Adds a patient named John Doe who is a Male, 26 years old, has AB+ Blood Type, and is allergic to Penicillin.
His email and phone number is johndoe@gmail.com and 12345678, respectively.

### Adding Medical Records : `addrecord`

Adds a patient's medical record.

Format: `addrecord PATIENTID d/DATETIME c/CONDITIONS... [m/MEDICATION]...`

- Adds a medical record to the patient with the corresponding `PATIENTID`

Constraints

- `DATETIME`: Valid Date and Time format: `DD-MM-YYYY HHMM`
- `CONDITION`: Up to 256 alphanumeric characters
- `MEDICATION`: Up to 256 alphanumeric characters

Examples:

- `addrecord 3 d/18-09-2023 1800 c/Fever m/Paracetamol`
  Adds a record to the patient with the `PATIENTID` of `3`.
  The record says that the patient visited the clinic on September 18th, 2023 at 6PM with a Fever and was prescribed Paracetamol.

### Listing all persons : `list`

Shows a list of all patients in the Medbook.

Format: `list`

- All patients in the Medbook will always be visible on the screen in the **MIDDLE** column.

Examples:

- `list` lists all the patients in the `MIDDLE` column.

### View patient records : `view`

Displays the medical records of the specified patient in the **RECORDS** list

Format: `view PATIENTID`

- The medical records of the patient with the corresponding `PATIENTID` will be displayed on screen in the **RECORD** list.
- Information about the patient will be displayed in the **PATIENT BEING VIEWED** section.

Examples:

- `view 2` displays the medical records of the patient with the `PATIENTID` of `2`. The displayed records will be in the `RECORDS` list and patient information will be in the `PATIENT BEING VIEWED` section.

### Editing detail of a patient : `editpatient`

Edits an existing patient in MedBook.

Format: `editpatient PATIENTID/FIELD/NEWVALUE`

- Edits the patient at the specified `PATIENTID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
- The `FIELD` must be provided.
- Accepted `FIELD` : **name**, **email**, **phone**, **gender**, **age**, **blood**, **allergy**.
- Existing values in the `FIELD` will be updated to the `NEWVALUE` input.
- Constraints are as state in `addpatient` command.

Examples:

- `editdetail 1/email/johndoe_updated@gmail.com` Edits the email address of the 1st person to be `johndoe_updated@gmail.com`.
- `editdetail 2/phone/92345678` Edits the name of the 2nd person to be `92345678`.
- `editdetail 3/allergy/aspirin` Edits the allergy of the 3rd person to be `aspirin`.

### Editing a record of a patient : `editrecord`

Edits a record of an existing patient in MedBook.

Format: `editrecord PATIENTID/RECORDID FIELD/NEWVALUE`

- Edits a record at the specific `RECORDID` of the patient at the specified `PATIENTID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
- The `FIELD` must be provided.
- Accepted `FIELD` : **d** (for dateTime), **c** (for conditions).
- Existing values in the `FIELD` will be updated to the `NEWVALUE` input.
- Multiple editing of condition fields are allowed to represent a new set of conditions.
- Contraints are as stated in `addrecord` command.

Examples:

- `editrecord 1/1 d/25-10-2023 1200` Edits the dateTime of the 1st record of the 1st patient to be `25-10-2023 1200`.
- `editrecord 1/1 c/Fever` Edits the conditions of the 1st record of the 1st patient to be `Fever`.
- `editrecord 1/1 c/Headache c/Flu` Edits the conditions of the 1st  of the 1st patient to be `Headache, Flu`.

### Locating persons by keywords: `search`

Searches the patient with the corresponding `KEYWORD`

Format: `search KEYWORD...`

- The search is case-insensitive. e.g `Penicillin` will match `penicillin`
- A patient's name and details will be searched.
- Patients matching at least one keyword will be returned.
  e.g. `arm fracture` will return patients with `burnt arm` and `leg fracture`

Examples:

- `search Penicillin` returns a list of patients with `Penicillin` in their details.

### Deleting a patient : `delete`

Deletes the specified patient.

Format: `delete PATIENTID`

- Deletes the patient at the specified `PATIENTID`.

Examples:

- `delete 2` deletes the patient with the `PATIENTID` of `2` from the app.

### Pinning a Patient : `pin`

Pins the specified patient to the **Pinned Patient** list.

Format: `pin PATIENTID`

- The pinned patient will always be visible on screen in the **Pinned Patient** list.

Examples:

- `pin 2` pins the patient with the `PATIENTID` of `2` to the **Pinned Patient** list.

### Unpinning a Patient : `unpin`

Unpins the specified patient from the **Pinned Patient** list.

Format: `unpin PATIENTID`

- The `PATIENTID` for the unpin command corresponds to the ID as it appears in the **Pinned Patient** list.

Examples:

- `unpin 2` unpins the patient with the `PATIENTID` of `2` as per the **Pinned Patient** list.

### Adding an Appointment : `addappointment`

Adds an appointment.

Format: `addappointment PATIENTID n/APPOINTMENTNAME d/DATETIME`

- Adds an appointment with patient with the corresponding `PATIENTID` to the **Appointments** list.

Constraints:

- `DATETIME`: Must adhere to `DD-MM-YYYY HHMM` format
- `APPOINTMENTNAME`: Up to 256 alphanumeric characters

Examples:

- `addappointment 2 n/Eye Examination d/10-10-2023 1800` adds an Eye Examination for the patient with the `PATIENTID` of `2`. The appointment is set for 10th October 2023 at 6:00pm.

### Clearing all entries : `clear`

Clears all entries from the medbook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MedBook data is securely encrypted and saved to the hard disk automatically after any command that changes the data. This ensures the privacy and safety of your data. When MedBook is restarted, the data is decrypted and loaded back into the application.

### Editing the data file

MedBook data are saved automatically as a JSON file [JAR file location]/data/medbook.json. As the data is encrypted, users will not be able edit this files to directly make changes to the data files. This also prevents unauthorised access of the system.

<box type="warning" seamless>
Caution:
Do not make changes to the data file.
</box>

## FAQ

**Q**: How do I check my java version?<br>
**A**: Open a command prompt and type `java -version`. If you do not have Java installed, you can download it [here](https://www.oracle.com/java/technologies/downloads/#java11).

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and copy the data folder over to the same location in the other computer.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary


| Action              | Format, Examples                                                                                                                       |
| ------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| **Add Patient**     | `addpatient n/NAME e/EMAIL p/PHONE …​` <br> e.g., `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` |
| **Add Record**      | `addrecord PATIENTID c/CONDITION... d/DATETIME [m/MEDICATION]...`<br> e.g., `addrecord 2 c/Fever d/10-10-2020 1900 m/Painkiller`       |
| **Add Appointment** | `addappointment PATIENTID n/APPOINTMENTNAME d/DATETIME`<br> e.g., `addappointment 2 n/Eye Exam d/10-10-2020 1900`                      |
| **List**            | `list`                                                                                                                                 |
| **View**            | `view PATIENTID`<br> e.g., `view 2`                                                                                                    |
| **Edit Patient** | `editpatient PATIENTID/FIELD/NEWVALUE`<br> e.g.,`editpatient 1/email/johndoe_updated@gmail.com`                                        |
| **Edit Record**  | `editrecord PATIENTID/RECORDID FIELD/NEWVALUE`<br> e.g.,`editrecord 1/1 d/25-10-2023 1200`                 |
| **Search**          | `search [KEYWORDS]`<br> e.g., `find James Jake`                                                                                        |
| **Delete**          | `delete PATIENTID`<br> e.g., `delete 3`                                                                                                |
| **Pin**             | `pin PATIENTID`<br> e.g., `pin 2`                                                                                                      |
| **Unpin**           | `unpin PATIENTID`<br> e.g. `unpin 2`                                                                                                   |
| **Clear**           | `clear`                                                                                                                                |
| **Exit**            | `exit`                                                                                                                                 |

