<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome to Lovelyz World</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
     <!-- jQuery -->
    <script src="../js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</head>

<body>

	<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title">Lovelyz World 회원 가입</h4>
        </div>
        
        <div class="modal-body">
        
          <form action="users/signup" method="post" class="form-horizontal">
  			
  			<div class="form-group">
    		<label for="inputId3" class="col-sm-3 control-label">* ID</label>
    			<div class="col-sm-6">
      				<input type="text" class="form-control" id="inputId3" name="userId">
      			</div>
   				<div class="col-sm-3">
   				 	<button id="btn_chk" type="button" class="btn btn-default">ID중복확인</button>  
   				</div>   				 
 			 </div>
  			
  			<div class="form-group">
    		<label for="inputPassword3" class="col-sm-3 control-label">* 비밀번호</label>
    			<div class="col-sm-6">
     	 			<input type="password" class="form-control" id="inputPassword3" name="userPw" placeholder="8자 이상으로 입력하세요.">
    			</div>
  			</div>
  			
  			<div class="form-group">
  			<label for="inputPassword3" class="col-sm-3 control-label">* 비밀번호 확인</label>
    			<div class="col-sm-6">
     	 			<input type="password" class="form-control" id="inputPassword3-confirm"  placeholder="위와 동일한 비밀번호를 입력하세요." >
    			</div>
  			</div>
  			
  			<div class="form-group">
  			<label for="inputName3" class="col-sm-3 control-label">* 이름</label>
    			<div class="col-sm-6">
     	 			<input type="text" class="form-control" id="inputName3" name="userName" >
    			</div>
  			</div>
  			
  			<div class="form-group">  				
  				<label for="inputSex3" class="col-sm-3 control-label">* 성별</label>
    				<div class="col-sm-6">    					
    					<label class="radio-inline">
    						<input type="radio" name="userSex" id="optionsRadios1" value="남" checked>남
   						</label>
   						<label class="radio-inline">
   							<input type="radio" name="userSex" id="optionsRadios2" value="여">여
					 	</label>			
  					</div>  				
			</div>
			
			<div class="form-group">
  			<label for="inputEmail3" class="col-sm-3 control-label">* 이메일</label>
    			<div class="col-sm-6">
     	 			<input type="email" class="form-control" id="inputEmail3" name="userEmail" placeholder="example@lovelyz.com">
    			</div>
  			</div>
			
  			
        
        <div class="modal-footer">
          <input type="submit" class="btn btn-default" value="완료" onclick="return confirm('회원 가입을 완료하시겠습니까?')">
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>      
    </div>
   </div>
  </div></form>
 


    
   
    <div class="brand">Lovelyz World</div>        
    <div class="address-bar">L O V E L Y Z<br>지금 , 우리</div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">Lovelyz World</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/lovelyz">Home</a>
                    </li>
                    <li>
                        <a href="../html/member.html">Member</a>
                    </li>
                    <li>
                        <a href="../html/board.html">Board</a>
                    </li>
                    <li>
                        <a href="../html/gallery.html">Gallery</a>
                    </li>
                    <li>
                        <a data-toggle="modal" href="#myModal" style="color:red">SignUp</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <div class="container">
    	<div class="box">
 			<div class="address-bar">
    	<h3>lovelyz world에 오신 ${users.userName}님 환영합니다.</h3>        
        <a href="logout" ><button class="btn btn-default" onclick="return confirm('정말로 로그아웃하시겠습니까?')">로그아웃</button></a>
   			</div>
   		</div> 
   </div>
   
    <div class="container">
        <div class="row">
            <div class="box">               
                    <div class="col-lg-12 text-center">
                       <iframe width="1060px" height="700px"  src="https://www.youtube.com/embed/wMCoQaE0LvQ" frameborder="0" allowfullscreen></iframe>                                
                        </div> 
                    </div>
                </div>
        
       
   
    
        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
                    <div id="carousel-example-generic" class="carousel slide">
                        <!-- Indicators -->
                        <ol class="carousel-indicators hidden-xs">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img class="img-responsive img-full" src="../img/slide-1.jpg" alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src="../img/slide-2.jpg" alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src="../img/slide-3.jpg" alt="">
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                            <span class="icon-prev"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                    <h2 class="brand-before">
                        <small>Welcome to</small>
                    </h2>
                    <h1 class="brand-name">Lovelyz World</h1>
                    <hr class="tagline-divider">
                    <h2>
                        <small>By
                            <strong>S.G. Hong</strong>
                        </small>
                    </h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Lovelyz World
                        <strong>이용 규칙!</strong>
                    </h2>
                    <hr>
                    <img class="img-responsive img-border img-left" src="img/intro-pic.jpg" alt="">
                    <hr class="visible-xs">
                    <p>1. 모든 자료는 회원 가입 후에 이용하실 수 있습니다.</p>
                    <p>2. -----------------------------------------</p>
                    <p>3. ----------------------------------------</p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">New Content & Picture<br><br>
                        <strong>최신 글 & 사진</strong>
                    </h2>
                    <hr>
                    <p></p>
                    
                </div>
            </div>
        </div>

    
    <!-- /.container -->
    </div>
    
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; By S.G. Hong 2017.07.05~</p>
                </div>
            </div>
        </div>
    </footer>

   

</body>

</html>
