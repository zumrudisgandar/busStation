New features for “Bus station” project
Front tasks: Admin page
1.	Login page
1.1.	Forgot password page
1.1.1.	Input: Email
1.1.2.	If OTP: otp validation page
1.2.	Set new password page
1.2.1.	Inputs: Create New Password + Repeat password (If passwords doesn’t match return red error: Password doesn’t match, else: Password match
2.	Admin main page
2.1.	Bus Stations List
2.2.	Left side: Categories page
3.	Create new “Bus Station” page
3.1.	Inputs: All inputs from “Bus station" page
4.	Edit or delete “Bus station” page
4.1.	Inputs: All inputs from “Bus station" page

Backend tasks: Admin page
1.	Admin login (URL: admin/auth/login)
1.1.	Post: Email and Username
2.	Admin forgot password (with email)
2.1.	Post: Email
2.2.	Send url or OTP code to email
2.3.	If otp, validate it: URL: admin/validate/otp
3.	Set new password
4.	“Bus Station” CRUD
