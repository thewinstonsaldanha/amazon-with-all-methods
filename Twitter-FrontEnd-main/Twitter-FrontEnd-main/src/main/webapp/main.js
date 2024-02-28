var tweets = [];
var user = null;
function getTweets() {
  fetch("http://localhost:8080/postbook/webapi/twitter/tweets/getAllTweet", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      Object.assign(tweets, data);
      console.log("Data :", data);
      mapTweetsToCard(data);
    });
}

function getMyTweets() {
  fetch(
    `http://localhost:8080/postbook/webapi/twitter/tweets/myTweet/${user.userId}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }
  )
    .then((res) => res.json())
    .then((data) => {
      Object.assign(tweets, data);
      console.log("Data :", data);
      mapMyTweetsToCard(data);
    });
}

function mapTweetsToCard(tweets) {
  // loop the array....
  var listString = "";

  for (let i = 0; i < tweets.length; i++) {
    listString += `<div class="card mb-3">
    <div class="card-body">
        <div class="d-flex align-items-center">
            <img src="${tweets[i].user.userAvatar}" alt="User Avatar" class="rounded-circle me-2" style="width: 50px; height: 50px;">
            <div>
                <h5 class="card-title mb-0">${tweets[i].user.userName}</h5>
                <p class="card-subtitle text-muted">${tweets[i].user.userEmail} • ${tweets[i].tweetId} minutes ago</p>
            </div>
        </div>
        <p class="card-text mt-3">${tweets[i].tweetBody}</p>
        <div class="d-flex justify-content-between align-items-center mt-3">
            <div>
                <button type="button" class="btn btn-outline-primary btn-sm"><i class="bi bi-chat"></i> 10</button>
                <button type="button" class="btn btn-outline-danger btn-sm" onclick="increaseLikes(${tweets[i].tweetId})"><i class="bi bi-heart"></i> ${tweets[i].tweetLikes}</button>
            </div>
           
        </div>
    </div>
</div>
`;
  }

  document.getElementById("allTweetsList").innerHTML = listString;
}

function mapMyTweetsToCard(tweets) {
  // loop the array....
  var listString = "";

  for (let i = 0; i < tweets.length; i++) {
    listString += `<div class="card mb-3">
    <div class="card-body">
        <div class="d-flex align-items-center">
            <img src="${tweets[i].user.userAvatar}" alt="User Avatar" class="rounded-circle me-2" style="width: 50px; height: 50px;">
            <div>
                <h5 class="card-title mb-0">${tweets[i].user.userName}</h5>
                <p class="card-subtitle text-muted">${tweets[i].user.userEmail} • ${tweets[i].tweetId} minutes ago</p>
            </div>
        </div>
        <p class="card-text mt-3">${tweets[i].tweetBody}</p>
        <div class="d-flex justify-content-between align-items-center mt-3">
            <div>
                <button type="button" class="btn btn-outline-primary btn-sm"><i class="bi bi-chat"></i> 10</button>
                <button type="button" class="btn btn-outline-danger btn-sm" onclick="increaseLikes(${tweets[i].tweetId})"><i class="bi bi-heart"></i> ${tweets[i].tweetLikes}</button>
            </div>
            <button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteTweet(${tweets[i].tweetId})">Delete</button>
        </div>
    </div>
</div>
`;
  }

  document.getElementById("myTweetsList").innerHTML = listString;
}

function signUp() {
  const userData = {
    userName: document.getElementById("signUpName").value,
    userEmail: document.getElementById("signUpEmail").value,
    userPassword: document.getElementById("signUpPassword").value,
  };
  //console.log(userName);
  document.getElementById("signUpEmail").value = "";
  document.getElementById("signUpName").value = "";
  document.getElementById("signUpPassword").value = "";

  fetch("http://localhost:8080/postbook/webapi/twitter/users/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userData),
  })
    .then((resp) => {
      if (resp.status === 200) {
        alert("User Register Successfully !");
        return resp.json();
      } else if (resp.status === 204) {
        // No content
        throw new Error("Wrong email or password");
      }
    })
    .then((data) => console.log(data));
}

function signIn() {
  const userEmailInput = document.getElementById("signInEmail");
  const userPasswordInput = document.getElementById("signInPassword");
  const userData = {
    userEmail: userEmailInput.value,
    userPassword: userPasswordInput.value,
  };
  document.getElementById("signInEmail").value = "";
  userPasswordInput.value = "";
  //console.log(userName);

  fetch("http://localhost:8080/postbook/webapi/twitter/users/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userData),
  })
    .then((resp) => {
      if (resp.status === 200) {
        // Successful login
        console.log("success");
        alert("Logged In Successfully!");
        document.getElementById("feed-tab").disabled = false;
        document.getElementById("profile-tab").disabled = false;
        document.getElementById("my-tweets-tab").disabled = false;
        document.getElementById("sign-in-tab").hidden = true;
        document.getElementById("sign-up-tab").hidden = true;
        document.getElementById("logout-tab").hidden = false;
        getTweets();
        const feedTabButton = document.getElementById("feed-tab");
        feedTabButton.click();
        //document.getElementById("alert").className=document.getElementById("alert").className+" alert alert-success";
        //document.getElementById("alertTitle").innerText="Success";
        //document.getElementById("alertMsg").innerText="Sign In Successfully !";
        // document.getElementById("alertMsg").hidden=false;
        //alert("Please enter correct details ");
        //document.getElementById("alertClose").hidden=false;
        return resp.json();
      } else if (resp.status === 204) {
        // No content
        throw new Error("Wrong email or password");
      }
    })
    .then((data) => {
      user = data;
      console.log(user);
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("Email Id and Password is Wrong !");
      // document.getElementById("alert").className=document.getElementById("alert").className+" alert alert-danger";
      //document.getElementById("alertTitle").innerText="Danger";
      //document.getElementById("alertMsg").innerText="Please enter correct details ";
      //document.getElementById("alertMsg").hidden=false;
      //alert("Please enter correct details ");
      //document.getElementById("alertClose").hidden=false;
    });
}

function addTweet() {
  //const userName=document.getElementById("name").value;
  const tweetBody = document.getElementById("body").value;
  console.log(tweetBody);
  const tweetData = {
    tweetBody: tweetBody,
    user: {
      userId: user.userId,
    },
  };
  fetch("http://localhost:8080/postbook/webapi/twitter/tweets/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(tweetData),
  })
    .then((resp) => {
      getTweets();
      resp.json();
    })
    .then((data) => console.log("Tweet Data :", data));
}

function deleteTweet(tweetId) {
  console.log("Tweet Id", tweetId);
  fetch(
    `http://localhost:8080/postbook/webapi/twitter/tweets/deleteTweet/${tweetId}`,
    {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }
  )
    .then((resp) => {
      getMyTweets();
      resp.json();
    })
    .then((data) => console.log("delete Data :", data));
}

function increaseLikes(tweetId) {
  console.log("Tweet Id", tweetId);
  fetch(
    `http://localhost:8080/postbook/webapi/twitter/tweets/likes/${tweetId}`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
    }
  )
    .then((resp) => {
      getTweets();
      getMyTweets();
      resp.json();
    })
    .then((data) => console.log("delete Data :", data));
}

function logout() {
  document.getElementById("feed-tab").disabled = true;
  document.getElementById("profile-tab").disabled = true;
  document.getElementById("my-tweets-tab").disabled = true;
  document.getElementById("sign-in-tab").hidden = false;
  document.getElementById("sign-up-tab").hidden = false;
  document.getElementById("logout-tab").hidden = true;

  const signInTabButton = document.getElementById("sign-in-tab");
  signInTabButton.click();
}

function profile() {
  document.getElementById("nameTextBox").value = user.userName;

  document.getElementById("emailTextBox").value = user.userEmail;

  document.getElementById("passTextBox").value = user.userPassword;

  document.getElementById("bioTextBox").value = "";

  document.getElementById("profile-image").src = "";
  console.log("Image URL :", document.getElementById("profile-image").src);
  document.getElementById("edit-input").hidden = false;
  document.getElementById("input").hidden = true;
}

function onEdit() {
  document.getElementById("nameTextBox").value =
    document.getElementById("name-text").innerText;

  document.getElementById("emailTextBox").value =
    document.getElementById("email-text").innerText;

  document.getElementById("passTextBox").value =
    document.getElementById("pass-text").innerText;

  document.getElementById("bioTextBox").value =
    document.getElementById("bio-text").innerText;

  document.getElementById("AvatarTextBox").value =
    document.getElementById("profile-image").src;
  document.getElementById("edit-input").hidden = false;
  document.getElementById("input").hidden = true;
}

function onSave() {
  document.getElementById("name-text").innerText =
    document.getElementById("nameTextBox").value;

  document.getElementById("email-text").innerText =
    document.getElementById("emailTextBox").value;

  document.getElementById("pass-text").innerText =
    document.getElementById("passTextBox").value;

  document.getElementById("bio-text").innerText =
    document.getElementById("bioTextBox").value;

  document.getElementById("profile-image").src =
    document.getElementById("AvatarTextBox").value;
  document.getElementById("edit-input").hidden = true;
  document.getElementById("input").hidden = false;
}
