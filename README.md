# github-user-search-android

<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- ABOUT THE PROJECT -->
## About The Project

A GitHub User Search app built using modern Android development tools and clean architecture.
This app allows users to search for GitHub users, view the list of results, and explore detailed information about a selected user.
It uses the GitHub REST API and persists search results locally using Room for offline access.

This project focuses on best practices such as:

* Separation of concerns with MVVM
* Modular code organization
* Unidirectional data flow using Flow
* Dependency injection with Hilt
* Caching data using Room

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* Programming Language : [Kotlin](https://kotlinlang.org/)
* UI : Jetpack Compose & Navigation
* Design Architecture : MVVM
* Dependency Injection : Hilt
* Async : Flow & Coroutine
* Network : Retrofit & Gson
* Local Database : Room
* Debugging : Chucker

### Features
* Search GitHub users via REST API
* View detailed information about a selected user (name, username, avatar, etc.)
* Cache search results locally using Room for offline access
* Reactive data stream with Kotlin Flow for real-time updates
* Navigation using Jetpack Compose Navigation
* Modular and testable codebase structure
* Jetpack Compose UI that adapts to state changes

### Improvements
* Added offline support via Room to allow users to revisit previous search results without re-querying the API.
* Implemented a debounce mechanism to avoid excessive API calls during search.
* Introduced loading and error UI states for better UX feedback.
* Modularized the project to separate concerns and improve maintainability.

### Challenges
* Handling pagination or rate limiting from GitHub API (initially planned but not yet fully implemented).
* Managing proper data flow between remote and local data sources while maintaining performance and data consistency.
* Debugging Compose state hoisting and recomposition issues, especially during screen transitions.
* Create unit tests with a tight deadline. (not implemented)

### Architecture Pattern
This app uses MVVM (Model-View-ViewModel) architecture combined with Clean Architecture principles to ensure:
* Separation of concerns: UI, domain, and data layers are clearly separated.
* Testability: Each layer is independent and easier to test in isolation.
* Maintainability: Features can be extended or refactored with minimal impact on unrelated parts.
* Modularity: Each module (e.g., data, domain, presentation) is independently structured for scalability.

Layers:
* Presentation – Jetpack Compose UI, ViewModel
* Domain – Repository interfaces, Models
* Data – Repository implementations, Room, Retrofit

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

* Clone the repo
  ```sh
  git clone https://github.com/bhrbyan/github-user-search-android.git
  ```
* Run the project

<!-- CONTACT -->
## Contact

Bhrahar Biandaru Wibisono - [LinkedIn](https://www.linkedin.com/in/bhrahar-biandaru-wibisono-8a3570147/) - bhrahars@gmail.com

Project Link: [GithubUserSearch](https://github.com/bhrbyan/github-user-search-android)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
