# PhotoTag App
-----------------
The PhotoTag application is an Android project that enables users to manage their photos by associating them with descriptive tags. The app allows users to search for photos, save them, and manage their own photo collections. The app also features functionality to tag photos with details like artist name and date, which are stored in a local database.

## Features
- **Home Screen**: Displays a list of all saved photo tags in the database.
- **Photo Search**: Users can search for photos through an external API (e.g., Pixabay) and view them in a grid.
- **Detail Screen**: Users can view the details of a selected photo and add additional information to their photos.
- **Delete Functionality**: Users can delete photos from the home screen by swiping them.
- **Splash Screen**: A simple opening screen that navigates the user to the home screen.


## Used Technologies 🛠️

- **[Kotlin](https://kotlinlang.org/)**: The programming language used for Android development. It is modern, secure, and efficient, offering a safer and more concise syntax compared to Java.
- **[Android SDK](https://developer.android.com/studio)**: The Software Development Kit required for developing Android applications. It includes tools and libraries needed to build, test, and debug Android apps.
- **[Android Jetpack](https://developer.android.com/jetpack)**: A set of libraries, tools, and architectural guidance to help developers write high-quality Android apps. The following Jetpack components are used in this project:
  - **[Room](https://developer.android.com/training/data-storage/room)**: A database library that simplifies data storage and retrieval in SQLite databases with an abstraction layer, providing a more robust and secure approach to managing app data.
  - **[LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData)**: A lifecycle-aware data holder class that allows UI components to observe changes in data in a way that respects the Android lifecycle.
  - **[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)**: A class that is responsible for preparing and managing data for the UI. It allows data to survive configuration changes such as screen rotations.
  - **[Navigation Component](https://developer.android.com/guide/navigation)**: A library for handling navigation within Android apps. It simplifies the process of navigating between fragments and passing data safely between them.
  - **[Fragment](https://developer.android.com/guide/fragments)**: A modular section of an Android activity that allows for reusable UI components and easier management of large or complex UIs.
  - **[ViewBinding](https://developer.android.com/topic/libraries/view-binding)**: A feature that allows you to more easily write code that interacts with views. It eliminates the need for `findViewById()` by generating a binding class for each XML layout file.

- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**: A dependency injection library built on top of Dagger to simplify DI in Android applications. It is used in this project to manage dependencies like ViewModels and Repositories in a clean and scalable way.

- **[Retrofit](https://square.github.io/retrofit/)**: A type-safe HTTP client for making network requests. It is used to handle API calls and automatically parse responses into data objects in this project.

- **[Glide](https://github.com/bumptech/glide)**: An image loading and caching library. It is used to load images from the network into the app's UI efficiently and with minimal memory usage.

- **[Material Components](https://material.io/develop/android)**: A library that provides Material Design components and patterns for Android apps. It helps with creating intuitive and consistent UI designs, offering components like Floating Action Buttons, Buttons, Cards, and more.
- **[RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView)**: A flexible view for providing a limited window of large data sets. It is used in this project to display photo tags and other data in a scrollable list.

- **[StaggeredGridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager)**: A layout manager used with RecyclerView that arranges items in a staggered grid, which is particularly useful when displaying items of varying sizes.

- **[Floating Action Button](https://material.io/components/floating-action-button)**: A button used for primary actions in the app. It provides easy access to common user interactions.

## Preview 🎞️
https://github.com/user-attachments/assets/206d35f7-d65b-46ee-b8ae-421f4447ecd7

## Screenshots 📸
| Splash Screen | Home Screen | Detail Screen | ApiGallery Screen |
|----------------|--------------|----------------|---------------|
| ![Image](https://github.com/user-attachments/assets/8468f129-58b2-4e1d-9eac-87c579677760) | ![Image](https://github.com/user-attachments/assets/b66e2d29-08b2-4531-b3e5-9c17f3aedc87) | ![Image](https://github.com/user-attachments/assets/ffd9c064-e7bc-4e35-8b7f-7628b524b043) | ![Image](https://github.com/user-attachments/assets/6bdb7ba3-6dee-417e-972e-53ba4d9f8bea) |

