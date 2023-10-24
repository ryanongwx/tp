---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# MedBook User Guide

Medbook is a **desktop app for managing patient details and medical records, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MedBook can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `medbook.jar` from [here](https://github.com/AY2324S1-CS2103T-T12-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MedBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar medbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` : Adds a new patient named John Doe with basic information about the patient 

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- `PATIENTID` refers to the unique patient ID shown in the displayed patient list. The ID **must be a positive integer** 1, 2, 3, …​

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a patient: `addpatient`

Adds a patient's contact and medical details.

Format: `addpatient n/NAME e/EMAIL p/PHONE g/GENDER a/AGE bt/BLOODTYPE al/ALLERGIES`
- Adds a patient with information including: `NAME`, `EMAIL`, `GENDER`, `PHONE`, `AGE`, `BLOODTYPE`, `ALLERGIES`

Constraints
- `NAME`: Must have first and last name, a space in between the names
- `EMAIL`: Must adhere to the following format: `email@domain.com`
- `GENDER`: Must be one of `M` or `F`
- `PHONE`: Must be 8 digits in length
- `AGE`: Must be an integer greater than or equal to 1
- `BLOODTYPE`: Must be one of `A-`, `A+`, `B-`, `B+`, `AB-`, `AB+`, `O-`, `O+`
- `ALLERGIES`: Must be alphanumeric characters 

Examples:

- `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` 
adds a patient named John Doe who is a male, 26 years old, has AB+ blood type, and allergic to penicillin.
His email and phone number is johndoe@gmail.com and 12345678, respectively.

### Adding Medical Records : `addrecord`

Adds a new patient's medical record to the app.

Format: `addrecord PATIENTID d/DATETIME c/CONDITION`
- Adds a medical record to the patient with the `PATIENTID`

Constraints
- `DATETIME`: Must adhere to the `DD-MM-YY HHMM` format
- `CONDITION`: Can have up to 256 alphanumeric characters

Examples:
- `addrecord 3 d/18-09-2023 1800 c/Fever`
adds a record to the patient who has the `PATIENTID` of `3`. 
The record says that the patient visited the clinic on September 18th, 2023 at 6PM due to fever.

### Listing all persons : `list`

Shows a list of all patients in the Medbook.

Format: `list`

### View patient detail

Shows the detailed information of the patient by clicking.

### Editing detail of a patient : `editdetail`

Edits an existing person in MedBook.

Format: `editdetail [patient ID/detail field/updated patient details]`

- Edits the person at the specified `patient ID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- The `detail field` must be provided.
- Accepted `detail field` : **name**, **email**, **phone**, **gender**, **age**, **blood**, **allergy**.
- Existing values in the `detail field` will be updated to the `updated patient details` input.
- **Name**: Text (up to 256 characters)
- **Email**: Valid email format
- **Phone**: Number (up to 15 digits)
- **Gender**: M/F
- **Age**: Number (0-120)
- **Blood Type**: One of [A+, A-, B+, B-, AB+, AB-, O+, O-]
- **Allergies**: Text (up to 512 characters)

Examples:

- `editdetail 1/email/johndoe_updated@gmail.com` Edits the email address of the 1st person to be `johndoe_updated@gmail.com`.
- `editdetail 2/phone/92345678` Edits the name of the 2nd person to be `92345678`.
- `editdetail 3/allergy/aspirin` Edits the allergy of the 3rd person to be `aspirin`.

### Locating persons by keywords: `search`

In MedBook, the Search functionality streamlines the process of finding patients based on specific keywords. Simply enter the keyword, and MedBook will display a list of patients matching your search criteria. If no matches are found, the system will provide feedback, allowing you to refine your search for better results.

Format: `search [Keyword]`

- The search is case-insensitive. e.g `Penicillin` will match `penicillin`
- A patient's name and details will be searched.
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `arm fracture` will return `burnt arm`, `leg fracture`

Examples:

- `search Penicillin` returns `Penicillin`

### Deleting a patient : `delete`

Deletes the specified patient from the medbook.

Format: `delete [patientId]`

- Deletes the patient at the specified `patientId`.
- The patientId refers to the unique patient id shown in the displayed patient list.
- The id **must be a positive integer** 1, 2, 3, …​

Examples:

- `delete 2` deletes the patient with the id of `2` in the medbook.

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

Adds an appointment to the application.

Format: `addappointment PATIENTID n/APPOINTMENTNAME d/DATETIME`

- Adds an appointment with the corresponding patient with the `PATIENTID` to the **Appointments** list and `APPOINTMENTNAME` and `DATETIME`.

Constraints:

- `DATETIME`: Must adhere to `DD-MM-YY HHMM` format
- `APPOINTMENTNAME`: Can have up to 256 alphanumeric characters

Examples:

- `addappointment 2 n/Eye Examination d/10-10-2023 1800` adds an Eye Examination for the patient with the `PATIENTID` of `2`. The appointment is set for 10th October 2023 at 6:00pm and will be added to the **Appointments** list.

### Clearing all entries : `clear`

Clears all entries from the medbook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MedBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MedBook data are saved automatically as a JSON file `[JAR file location]/data/medbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, MedBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedBook home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **List**   | `list`                                                                                                                                                                |
| **Edit**   | `editdetail [patient ID/detail field/updated patient details]`<br> e.g.,`editdetail 1/email/johndoe_updated@gmail.com`                                                |
| **search** | `search KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                        |
| **Delete** | `delete [patientId]`<br> e.g., `delete 3`                                                                                                                             |
| **Pin**    | `pin [patientId]`<br> e.g., `pin 2`                                                                                                                                   |
| **Clear**  | `clear`                                                                                                                                                               |
| **exit**   | `exit`                                                                                                                                                                |
