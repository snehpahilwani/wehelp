var restify = require('restify');
 var server = restify.createServer({ name: 'mongo-api' })
 server.listen(7000, function () {
 console.log('%s listening at %s', server.name, server.url)
});

 var AWS = require("aws-sdk");
AWS.config.loadFromPath('./config.json');
AWS.config.update({
  region: "us-east-2",
  //endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();
// var server = require('http').createServer().listen(7000);
// var io = require('socket.io').listen(server);
// io.sockets.on('connection', function(client){
//     client.on('message', function(err, msg){
//         client.broadcast.emit('message', msg);
//     });
//  });



// console.log("Scanning Movies table.");
// docClient.scan(params, onScan);
server
 .use(restify.fullResponse())
 .use(restify.bodyParser());

 server.get('/sendnoti', function (req, res, next) {
 // User.find({}, function (error, users) {
 // res.send(users)
 // })
 	var FCM = require('fcm-node');

	var serverKey = 'AIzaSyCyKLTGHL7HkEwihP_pXh9PaW4tyuF8wbg';
	var fcm = new FCM(serverKey);

	var message = { //this may vary according to the message type (single recipient, multicast, topic, et cetera)
    to: 'edes-dnHxac:APA91bGPphpTi67VJ2VkWEYYMDfrcSNsCh1wWHIVXlo4XrGFgtYY43nZPrYlmuYDom64m6LzflcrF38jquPhCdMB-cV023CzWRMHvrJA4RhvuQaDPmWUIz40t8RIlDer5kG0yDLQ-anQ', 
    //collapse_key: 'your_collapse_key',
    
    notification: {
        title: 'Title of your push notification', 
        body: 'Body of your push notification' 
    },
    
    data: {  //you can send only notification or only data(or include both)
        my_key: 'my value',
        my_another_key: 'my another value'
    }
	};

	fcm.send(message, function(err, response){
    if (err) {
        res = "Something has gone wrong!";
    } else {
        res = "Successfully sent with response: "+ response;
    }
	});
	return res;
});

// server.get('/getList', function (req, res, next) {
// 	res = 
// }


function onScan(err, data) {
    if (err) {
        console.error("Unable to scan the table. Error JSON:", JSON.stringify(err, null, 2));
    } else {
        // print all the movies
        console.log("Scan succeeded.");
        // console.log(data.Items);
        var values = [];
        data.Items.forEach(function(req) {
           rvalues.push("ReqId: "+req.reqid + " Mid: ",
                req.mid, " Vid:", req.vid);
        });
		console.log(values);
        return data.Items;
        // data.Items.forEach(function(req) {
        //    return "ReqId: "+req.reqid + " Mid: ",
        //         req.mid, " Vid:", req.vid;
        // });

        // continue scanning if we have more movies, because
        // scan can retrieve a maximum of 1MB of data
        if (typeof data.LastEvaluatedKey != "undefined") {
            console.log("Scanning for more...");
            params.ExclusiveStartKey = data.LastEvaluatedKey;
            docClient.scan(params, onScan);
        }
    }
}

function returnList(){
	var params = {
    TableName: "Requests",
    ProjectionExpression: "#reqid, mid, vid, completed",
    FilterExpression: "completed=:comp",//#yr between :start_yr and :end_yr",
    ExpressionAttributeNames: {
        "#reqid": "reqid",
    },
    ExpressionAttributeValues: {
         ":comp": 0
         //":end_yr": 1959 
    }
	};
	var request = docClient.scan(params, onScan);

	request.on('success', function(response) {
		console.log(response);
		// console.log(response.Items);
		var values = [];
		response.forEach(function(req) {
           values.push("ReqId: "+req.reqid + " Mid: ",
                req.mid, " Vid:", req.vid);
        });
		console.log(values);
	});
	//console.log("Global data: "+globalData);
	return globalData;
	//var val = onScan();
}

//  db = mongoose.connect("mongodb://localhost/example-api");
// var Schema = mongoose.Schema,
//  ObjectId = Schema.ObjectID;
// var User = new Schema({
//  email: {
//   type: String,
//   required: true,
//   trim: true
//  },
//  fname: {
//   type: String,
//   required: false,
//   trim: true
//  },
//  lname: {
//   type: String,
//   required: false,
//   trim: true
//  },
// });
// var User = mongoose.model('User', User);




//  server.post('/user', function (req, res, next) {
//  if (req.params.email === undefined) {
//   return next(new restify.InvalidArgumentError('Email must be supplied'))
//  }
//  var userData = {
//   email: req.params.email,
//   fname: req.params.fname,
//   lname: req.params.lname
//  }
//  var user = new User(userData);
//  user.save(function (error, data) {
//   if (error) {
//    return next(new restify.InvalidArgumentError(JSON.stringify(error.errors)))
//   }
//   else {
//    res.json(data);
//   }
//   res.send(201, user)
//  })
// });