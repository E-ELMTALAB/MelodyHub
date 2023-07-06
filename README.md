# MelodyHub

![header](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/1d4d9ddb-7cf3-4fbd-b70c-8c1f0e65ae18)


MelodyHub is a music player application developed in [`java`](https://www.java.com/en/download/help/whatis_java.html) , [`python`](https://www.python.org/doc/essays/blurb/) , [`C++`](https://sefiks.com/2018/08/06/deep-face-recognition-with-keras/) and [`flutter`](https://sefiks.com/2018/08/06/deep-face-recognition-with-keras/) . It offers features like song playback, playlist creation, search functionality, sharing songs and playlists, artist and podcaster access, adding favorites, playing songs from playlists and ... ,  you also get personalized song [recommendations](https://sefiks.com/2018/08/06/deep-face-recognition-with-keras/).


# Description

this project consists of four different sections :, 
 Server , Client , Database , Recommendation Engine 
 

 lets go through all of the different sections and see what role do they play in the project 

## Server

The application utilizes two separate servers for different functionalities. The first server handles the generation and delivery of temporary passwords during the login process. This server ensures that users receive the necessary credentials securely and reliably.

The second server is responsible for various operations, including song downloading, extraction of song images, handling follow functionalities, managing playlist creation, processing song information requests, and more. It serves as a central hub for multiple tasks related to the application's core functionalities.

To ensure data security, modern encryption methods such as AES (Advanced Encryption Standard) and RSA (Rivest-Shamir-Adleman) are employed. AES is a symmetric algorithm designed for efficient data encryption and decryption, while RSA is an asymmetric method primarily used for secure key exchange and digital signatures. These encryption techniques enhance the confidentiality and integrity of sensitive information.

Moreover, all user passwords and essential information are stored using secure hash methods. Hashing algorithms convert sensitive data into unique hash values, making it computationally infeasible to retrieve the original information from the hash. This further safeguards user passwords and essential data from unauthorized access.

By employing these encryption and hashing techniques, the application ensures a robust security framework, protecting user information and enhancing overall data privacy.






Regenerate response
## Client

The client interface serves as the front-end of the application, offering various functionalities to users. These functionalities include playing songs, sharing songs and playlists, following other users, displaying popular and recommended songs, accessing premium status, liking songs, creating playlists, and downloading songs.

Users have the option to search for specific songs or add popular songs to their playlists or queue for playback and enjoyment. To acquire premium status within the application, users are required to play a game of Pac-Man, implemented in C++. By achieving a score above a specified threshold, users unlock the premium status. With premium status, users gain the ability to follow other users, view their followers and followings, and search for individuals within the application to follow.

The client interface offers a user-friendly experience, providing a range of features that enhance the overall music listening and social interaction within the application.

## Recommendation Engine
The system consists of two main components: a recommendation engine and a genre classification system. The recommendation engine operates continuously, providing song recommendations to users. When a user listens to more than four songs within a specific timeframe, the engine generates 15 song ID recommendations for that particular user and stores them in the database.

The genre classification system then checks if the recommended songs already have a genre assigned in the database. If they do, no further action is taken. However, if the songs do not have a genre assigned, the genre classification system listens to each song and determines their respective genres. These genre labels are then added to the database accordingly.

In summary, the system utilizes a recommendation engine to continuously provide song recommendations to users. The genre classification system ensures that songs without a pre-assigned genre are analyzed and labeled appropriately, enriching the database with accurate genre information.


## Databsae

The database used in the application is a comprehensive repository that stores information related to users, artists, podcasters, songs, playlists, ownership details, shared content, favorite items, user history, notifications, song lyrics, and user followers/followings.

Specifically, the song table in the database contains 588,000 unique song IDs along with associated information that users can listen to. The database is populated with data before, during, and after the application's operation using Python and Java scripts.


## Screenshots
Included below are several images showcasing the functionality  of the project

- loging page
![Screenshot (225)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/1ce3ae44-acdc-4167-99c6-b5484c4f6bb2)
![Screenshot (226)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/89156170-9a45-4cfe-9476-7bd5809542ed)

- Recommendations : The recommendations can be seen on the top scroll pane
![Screenshot (218)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/60f6be16-33ca-4b55-b61b-5192f76a006b)

- Search page
![Screenshot (219)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/aa3fb246-e173-44a8-8c78-0f31bd733d50)
- Profile page
![Screenshot (220)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/e8ebca76-3e99-47b1-a86c-f6860fc6fb91)

- Favorites page
![Screenshot (221)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/1990955f-ff82-4e79-85ea-4743630107f5)

- Song info page
![Screenshot (222)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/2d04b2ba-2ff2-423f-9598-b9c097717e6b)

- Queue page
![Screenshot (223)](https://github.com/bnsreenu/python_for_microscopists/assets/117757969/203cc911-c65d-4f3c-87cc-07f704aff0d3)


## Additional Features


- The application offers a light/dark mode toggle, allowing users to switch between different visual themes based on their preference.
- To assist users who have forgotten their passwords, a security question feature is implemented. Users can set up a security question during the account creation process, which can be used to reset the password in case it is forgotten.
- In the event of a forgotten password, users can retrieve a temporary password by utilizing the email verification method. The temporary password remains valid for a duration of two minutes and is automatically refreshed afterward.
- Users have access to their browsing history within the application, enabling them to review and revisit previously played songs or content.
- Users can receive notifications when other users choose to follow them, promoting social interaction and engagement within the application.
- The search page allows users to explore and access songs. It also includes the option to filter search results by multiple genres, allowing users to specifically search within their preferred genres.
- Users have the ability to upload their own profile image, enabling personalization and customization of their user profile.
- Users can view and play playlists that have been shared with them by others, fostering a collaborative and shared music listening experience.
- The initial page of the application is designed for unlogged users. It features a section where users can log in to their accounts to gain full functionality and access within the application.
- If a specific song is not available within the application's library, users can still play the song by downloading it from the server on-demand. This ensures a wide range of song availability and a seamless listening experience.
- All communication between the client and server is established through the socket communication method, ensuring secure and efficient data exchange.
- Users can utilize the search page to find and search for other users within the application. Additionally, they have the option to follow other users, enhancing social connections and networking within the application.
- The application begins with a project-related video, providing an introduction and overview. Users can choose to remain in the unlogged state or proceed with logging in to access the full functionality and features of the application.


## License

MelodyHub is licensed under the [MIT License](/LICENSE).


## Contributors

This project has been made and maintained by [E-ELMTALAB](https://github.com/E-ELMTALAB) , [seyed0123](https://github.com/seyed0123)  and [sabamadadi](https://github.com/sabamadadi)




