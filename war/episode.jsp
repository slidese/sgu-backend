<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/ic_launcher.png">

<title>SGU Metadata</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/jumbotron-narrow.css" rel="stylesheet">
<link href="css/sgu.css" rel="stylesheet">

</head>

<body>

	<div class="container">
		<div class="header">
			<!-- ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul-->
			<h3 class="text-muted">SGU Metadata</h3>
		</div>

		<div class="jumbotron">


			<!-- h1>Jumbotron heading</h1>
			<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas
				eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris
				condimentum nibh, ut fermentum massa justo sit amet risus.</p>
			<p>
				<a class="btn btn-lg btn-success" href="#" role="button">Sign up
					today</a>
			</p-->

			<p>Enter the episode number and click fetch to pre-populate the
				form with info from the SGU website</p>
			<form role="form">
				<div class="row">
					<div class="center-block">
						<input type="pre-id" class="form-control" id="inputPreId"
							placeholder="Episode ID" style="text-align: center;">
					</div>
				</div>
				<div class="row">
					<p></p>
					<button type="submit" id="fetchButton"
						class="btn btn-lg btn-success" data-loading-text="Loading...">Fetch</button>
				</div>
			</form>

			<!-- Add some space/padding -->
			<p></p>

		</div>

		<form role="form" action="/save" method="post">
			<div class="form-group">
				<label for="InputId">Episode #</label> <input type="text"
					class="form-control" id="InputId" placeholder="The episode ID" />
			</div>
			<div class="form-group">
				<label for="InputDuration">Duration</label> <input type="text"
					class="form-control" id="InputDuration"
					placeholder="72:14 for example" />
			</div>
			<div class="form-group">
				<label for="InputDescription">Description</label>
				<textarea class="form-control" rows="3" id="InputDescription"
					placeholder="The RSS description of the episode"></textarea>
			</div>
			<div class="form-group">
				<label for="InputGuid">GUID</label> <input type="text"
					class="form-control" id="InputGuid"
					placeholder="The unique ID for this episode as found in the RSS feed" />
			</div>
			<div class="form-group">
				<label for="InputTranscript">Transcript</label> <input type="text"
					class="form-control" id="InputTranscript"
					placeholder="The complete URL to the transcript page for this episode" />
			</div>
			<div class="form-group">
				<label for="InputImage">Image</label> <input type="text"
					class="form-control" id="InputImage"
					placeholder="The full filename of the image thumbnail for this episode" />
			</div>

			<!-- Hosts -->
			<div class="form-group">
				<label>Hosts</label>

				<div class="checkbox">
					<label> <input type="checkbox" checked="checked"> Steven Novella
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" checked="checked"> Bob Novella
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" checked="checked"> Jay Novella
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" checked="checked"> Rebecca Watson
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" checked="checked"> Evan Bernstein
					</label>
				</div>
			</div>

			<!-- The quote -->
			<div class="form-group">
				<label for="InputQoute">Quote</label>
				<textarea class="form-control" rows="3" id="InputQuote"
					placeholder="The quote"></textarea>
			</div>
			<div class="form-group">
				<label for="InputQuoteBy">By</label> <input type="text"
					class="form-control" id="InputQuoteBy" placeholder="By whom" />
			</div>

			<div style="margin-bottom: 80px"></div>

			<div class="alert alert-info">
				<h4>Hints</h4>
				When adding links to either "Science or fiction" or "Sections"
				please note:
				<ol>
					<li>To add multiple links to an item separate each link with a
						space</li>
					<li>Always include http://</li>
				</ol>
			</div>

			<!-- Science or fiction -->
			<label>Science or fiction</label>
			<table class="table table-striped" id="scienceOrFictionTable">
				<thead>
					<tr>
						<th></th>
						<th width="80">#</th>
						<th>Science?</th>
						<th>Description</th>
						<th>Links</th>
						<th>Tags</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>

			<div class="clearfix">
				<a class="pull-right" href="#" id="addScienceOrFictionLink">Add science or
					fiction item</a>
			</div>

			<div style="margin-bottom: 80px"></div>

			<!-- Science or fiction -->
			<label>Sections</label>
			<table class="table table-striped" id="sectionsTable">
				<thead>
					<tr>
						<th></th>
						<th>Title</th>
						<th>Start</th>
						<th>Link</th>
						<th>Tags</th>
						<th></th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<div class="clearfix">
				<a class="pull-right" href="#" id="addSection">Add section item</a>
			</div>

			<div style="margin-bottom: 80px"></div>

			<div class="clearfix">
				<button type="submit" class="btn btn-lg btn-primary pull-right">Submit</button>
			</div>

		</form>

		<div style="margin-bottom: 80px"></div>

		<div class="footer">
			<p>&copy; slide.se 2014</p>
		</div>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<!-- script src="js/jquery.1.10.2.min.js"></script-->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.tablednd.js"></script>
	<script src="js/jquery.jeditable.js"></script>

	<script type="text/javascript">
	
		$("#addScienceOrFictionLink").click(function() {
		    addScienceOrFictionRow("1", true, "Change this description", "", "");
		    resetEditables();
            resetTableDnD();
			return false;
		});
	
		$("#addSection").click(function() {
		    addSectionRow("", "Change this title", "0:00", "", "");
		    resetEditables();
            resetTableDnD();
			return false;
		});
		
		$(document).on("click", "a.deleteRow", function() {
		   deleteRow(this);
		   return false;
		});
		
        $("#fetchButton").click(function() {
            
            var btn = $(this);
            btn.button("loading");

            var preId = $("#inputPreId").val();
            var jqxhr = $.get("sgu_metadata", { inputPreId: preId }, function() {
                //alert("success");
            }, "xml").done(function(data) {
                
                var status = $(data).find("status");
                var episode = $(data).find("episode");
                var title = $(data).find("episode > title");
                var description = $(data).find("episode > description");
                var guid = $(data).find("episode > guid");
                var transcript = $(data).find("episode > transcript");
                var image = $(data).find("episode > image");
                var quote = $(data).find("quote > text");
                var by = $(data).find("quote > by");
                
                $("#InputId").val(preId);
                $("#InputDescription").val(description.text());
                $("#InputGuid").val(guid.text());
                $("#InputTranscript").val(transcript.text());
                $("#InputImage").val(image.text());
                $("#InputQuote").val(quote.text());
                $("#InputQuoteBy").val(by.text());
                
                $(data).find("episode > sections > section").each(function() {
                    //alert($(this).find("title").text());
                    addSectionRow("", $(this).find("title").text(), "0:00", $(this).find("link").text(), "");
                });
                
                $(data).find("episode > scienceorfiction > item").each(function() {
                    //alert($(this).find("title").text());
                    addScienceOrFictionRow($(this).find("title").text(), true, $(this).find("description").text(), $(this).find("link").text(), "");
                });
                
                //alert(status.text());
                
            }).fail(function(msg) {
                alert("Something went wrong, please check the episode ID.");
                
                console.log(msg);
                alert(msg);
            }).always(function() {
                //alert( "finished" );
                //if ($("#InputId").val().length > 0)
                btn.button("reset");
                //$("#sectionsTable").tableDnD();
                resetEditables();
                resetTableDnD();
            });
            
            return false;

        });
        
        function addSectionRow(number, title, start, link, tags) {
            // <tr style=\"display: none;\"><td><span class=\"triggeredit\">Name</span></td><td><span class=\"triggeredit\">Value</span></td><td><a href=\"#\" class=\"removeProperty\"><span class=\"glyphicon glyphicon-trash pull-right\"></span></a></td></tr>")
            $("<tr style=\"display: none;\"> \
            		<td class=\"dragHandle\"><span class=\"glyphicon glyphicon-move\"></span></td> \
					<td><span class=\"edit-textarea\">" + title + "</span></td> \
					<td><span class=\"edit\">" + start + "</span></td> \
					<td><a href=\"" + link + "\"><span class=\"glyphicon glyphicon-link\"></span></a></td> \
					<td><a href=\"" + tags + "\"><span class=\"glyphicon glyphicon-tags\"></span></a></td> \
					<td align=\"right\"><a href=\"#\" class=\"deleteRow\">Remove</a></td> \
					</tr>")
            .appendTo('#sectionsTable > tbody').fadeIn(200);
        }
        
        function addScienceOrFictionRow(number, science, description, link, tags) {
            $("<tr style=\"display: none;\"> \
            		<td class=\"dragHandle\"><span class=\"glyphicon glyphicon-move\"></span></td> \
					<td><span class=\"edit\">" + number + "</span></td> \
					<td>" + science + "</td> \
					<td><span class=\"edit-textarea\">" + description + "</span></td> \
					<td><a href=\"" + link + "\"><span class=\"glyphicon glyphicon-link\"></span></a></td> \
					<td><a href=\"" + tags + "\"><span class=\"glyphicon glyphicon-tags\"></span></a></td> \
					<td align=\"right\"><a href=\"#\" class=\"deleteRow\">Remove</a></td> \
					</tr>")
            .appendTo('#scienceOrFictionTable > tbody').fadeIn(200);
        }
        
        function deleteRow(row) {
            $(row).parent().parent().remove();
        }
        
        // Make sure we have version 0.7 or later for this to function
        function resetTableDnD() {
            $("#sectionsTable").tableDnD({
            	dragHandle: ".dragHandle"  
            });
            
            $("#scienceOrFictionTable").tableDnD({
            	dragHandle: ".dragHandle"  
            });
        }
        
        function resetEditables() {
            $(".edit").editable(function(value, settings) { 
    			console.log(this);
    			console.log(value);
    			console.log(settings);
    			return(value);
    		},
    		{ 
    		    height    :  "24px",
    		    width     :  "60px",
    		    cssclass  :  "editable-input"
    		});
            
            $(".edit-textarea").editable(function(value, settings) { 
    			console.log(this);
    			console.log(value);
    			console.log(settings);
    			return(value);
    		},
    		{ 
    		    type      :  "textarea",
    		    submit    :  "OK",
    		    height    :  "40px",
    		    width     :  "600px",
    		    cssclass  :  "editable-input"
    		});
        }
    </script>

</body>
</html>