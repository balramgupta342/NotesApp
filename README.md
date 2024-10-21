# 📒 Notes App

**Notes App** is a simple and intuitive note-taking application built using **Jetpack Compose** in Android. This app allows users to easily create, edit, delete, and manage their notes with a modern UI. The app demonstrates how to use Android’s **Jetpack Compose** for building a fully functional note-taking app with MVVM architecture and local storage.

## 🛠️ Features

- 📝 **Create Notes**: Quickly create new notes by simply typing.
- 🖊️ **Edit Notes**: Edit existing notes easily by selecting them.
- ❌ **Delete Notes**: Swipe to delete notes you no longer need.
- 📑 **List of Notes**: Display all notes in a clean, scrollable list.
- 🔍 **Search Notes**: Search for notes by title or content.
- 🌓 **Light/Dark Mode**: Supports light and dark themes using Material Design.
- 📱 **Responsive UI**: The app adjusts well to different screen sizes and orientations.

## 🚀 Technologies & Libraries Used

- **Jetpack Compose**: For building the UI in a declarative manner.
- **Kotlin**: As the programming language.
- **Room Database**: For local persistence of notes.
- **ViewModel**: For managing UI-related data in a lifecycle-conscious way.
- **Coroutines**: For background threading and non-blocking operations.
- **Dagger/Hilt**: For dependency injection.
- **Material Design**: To make the app visually appealing.

## 📱 Screenshots

| ![Screenshot 2024-07-26 144147](https://github.com/user-attachments/assets/fa6dcd21-9c53-475f-9a29-4d9a2e5c0add) | ![Screenshot 2024-07-26 144241](https://github.com/user-attachments/assets/45662823-ea04-4e8e-a04c-9c541d73295d) | ![Screenshot 2024-07-26 144333](https://github.com/user-attachments/assets/3185b4af-4838-4a9e-826c-2dac50fd5986) | ![Screenshot 2024-10-21 140457](https://github.com/user-attachments/assets/710ab2aa-d682-49f5-bb0c-8711fa6e87a7) |
|------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|

## 🏗️ Architecture

The Notes App follows the **MVVM (Model-View-ViewModel)** architecture, which ensures the separation of concerns and makes the app easier to test and maintain.

- **Model**: Handles the business logic and communication with the database (Room).
- **ViewModel**: Provides the necessary data to the UI and survives configuration changes.
- **View (Jetpack Compose)**: Renders the UI, interacts with the ViewModel and responds to user actions.

## 🛠️ Installation and Setup

To get started with the Notes App, follow these steps:

### Prerequisites

- Android Studio (Arctic Fox or newer)
- Kotlin 1.5+
- Gradle 7.0+
- Android SDK 21 or higher

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/notes-app.git
    ```

2. **Open in Android Studio**:
   - Open Android Studio.
   - Select "Open an existing project" and navigate to the cloned repository folder.

3. **Build the Project**:
   - Let Android Studio sync and build the project.

4. **Run the App**:
   - Connect an Android device or use an emulator to run the app.

## 💡 Usage

- **Add a New Note**: Tap on the "Add Note" button, type your note, and hit "Save".
- **Edit a Note**: Select any note from the list to open it in edit mode.
- **Delete a Note**: Swipe left or right on a note to delete it.
- **Search for a Note**: Use the search bar to filter notes by keywords.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Create a Pull Request.
