# Take Home Assignment - Xiaofeng Liu

This is Xiaofeng Liu's take home assignment. It is a great experience
to participate the interview in this way.

To let you know, You might notice that the style of the popup window is not the same as designed.
This is because I cannot see the design of the popup after clicking the list's item because the video link in the pdf is not clickable.


## 
## Architecture
MVVM

The project follows the MVVM pattern where the View (MainActivity.java)
is responsible for presenting the UI. 

ViewModel (MainViewModel.java, RepoViewModel.java) is used to
transforming the data from the Model and provides data streams to the View.

Model (DataManager.java, ApiService.java and Java Beans) is for http request and holds the data.

## Video
https://www.youtube.com/shorts/89rI3ZColqs

I made a demo video for it. Pls check the link above.

## ScreenShot

<div align="center">
    <img src="https://github.com/i-xfliu/Scotia/blob/main/main1.png" width="30%" height="50%"/> <img src="https://github.com/i-xfliu/Scotia/blob/main/main2.png" width="30%" height="50%"/> 
<img src="https://github.com/i-xfliu/Scotia/blob/main/main3.png" width="30%" height="50%"/>
</div>



## CustomView
### RoundImageView
for the user to adjust the radius of four corner freely.
#### Usage
app:left_top_radius to change the left top corner's radius

app:left_bottom_radius to change the left btm corner's radius

app:left_top_radius to change the left top corner's radius

app:left_bottom_radius to change the left btm corner's radius

## Testing

### Unit Test
#### GitApiTest.jave
This is to mock the Http request, and test the correctness of the Internet Request.

The test covers the two requests sent to GitHub, get the user info by id and get repos by id respectively.

### UI Test
#### MainActivityTest.java
This UI test covers the functions of the main page:
1. Input the user id in the edit text
2. Search Button clicks
3. The correctness of the User's git name (can be different from the user's input)
4. The display of RecyclerView
5. The display of the Popup Window


