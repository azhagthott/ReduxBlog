# ReduxBlog

ReduxBlog API (https://reduxblog.herokuapp.com/)

Libraries implemented in the project: 
 
`implementation 'com.google.code.gson:gson:2.8.5'`
`implementation 'com.squareup.retrofit2:retrofit:2.4.0'`
`implementation 'com.squareup.retrofit2:converter-gson:2.3.0'`

All the calls to the services are made through AsyncTask's method `doInBackground()`, 
then in the view, a local class is created where the method `onPostExecute` is overridden. 

