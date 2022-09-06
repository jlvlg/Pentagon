```mermaid
    classDiagram
    direction TB
    class User {
        -login: Login
        -following: List~Followable~
        -isAdmin: boolean
        +follow(Followable followable) void
        +unfollow(Followable followable) void
    }
    class Page {
        -owners: List~User~
        -name: String
        -image: String
        -description: String
        +addOwner(User user) void
        +removeOwner(User user) void
    }
    class Login {
        -username: String
        -password: String
        +authenticate(String password) boolean
        +updatePassword(String oldPassword, String newPassword) boolean
        +updateUsername(String password, String username) boolean
    }
    class Followable {
        <<abstract>>
        -followers: int = 0
        -active: boolean
        +follow() void
        +unfollow() void
    }
    class Profile {
        -scoreMeans: Map~String, float~
    }
    class Postable {
        <<abstract>>
        -author: User
        -text: String
        -creationDate: Date
        -likes: int
        -dislikes: int
        -active: boolean
        -edited: boolean
        +like() void
        +dislike() void
    }
    class Post {
        -page: Page
        -image: String
        -visibility: List<User>
        -title: String
    }
    class Comment {
        -postable: Postable
    }
    class Modification {
        -post: Postable
        -oldImage: String
        -oldText: String
        -date: Date
    }
    class Score {
        -score: int
        -author: User
        -profile: Profile
        -category: String
    }
    Followable <|-- User
    Followable <|-- Page
    User "1" *-- "1" Login
    User "1" -- "n" Postable
    User "n" --o "n" Page
    User "1" -- "n" Score
    Page "1" *-- "n" Post
    Postable "1" *-- "n" Modification
    Postable "1" *-- "n" Comment
    Profile "1" *-- "n" Score
    Page <|-- Profile
    Postable <|-- Post
    Postable <|-- Comment
```

<style>
    .mermaid {
        width:  210mm !important;
        height: 297mm !important;
    }
    .markdown-preview {
        padding: 0 !important;
    }
    svg {
        width: 100%;
        height: 100%;
    }
</style>