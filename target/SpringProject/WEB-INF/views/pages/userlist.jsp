<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
<h1 class="text-center">PROFILES</h1>

<div class="row bg-lightgrey mr-auto">
    <div class="col-3 form-group bg-light">
        <div class="container">
            <h2 class="header-panel font-weight-bold text-center">Options</h2>
                    <div class="row">
                        <div class="center-block text-center w-100">
                            <a  href="<c:url value='/newuser' />" class="btn btn-secondary btn-lg w-75" type="submit">Add User</a>
                        </div>
                    </div>
            <form:form method="POST" class="form-horizontal">
                <div class="row justify-content-center mt-3">
                    <select id="filterType0" class="form-control" name="filterType" title="Choose one filter">
                        <option selected value = "">Select a filter</option>
                        <option value = "firstname">Firstname</option>
                        <option value = "lastname">Lastname</option>
                        <option value = "countryname">Country</option>
            <%--                            <option value = "birthdate">Birthdate</option>--%>
            <%--                            <option value = "role">Role</option>--%>
            <%--                            <option value = "maritalStatus">Marital Status</option>--%>
            <%--                            <option value = "skill">Skill</option>--%>
                    </select>
                </div>
                <div class="row justify-content-center mt-3 mb-3">
                    <div class="input-group">
                        <input class="form-control" id="filterText0" type="search" size="40" placeholder="Field Name.." maxlength="30" name="filterText"/>
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-info"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>

            </form:form>
            <form action="Home" method="get">
                <c:if test="${!(filterType == null || filterType == '')}">
                    <div class="row">
                        <div class="col">
                            <p>Filter by:</p>
                            <p class="text-capitalize d-inline font-weight-bold">${filterType}</p>: "${filterText}"
                            <button type="submit" class="close"><span aria-hidden="true">&times;</span></button>
                        </div>
                    </div>
                </c:if>
            </form>
       </div>
    </div>



    <div class="col-9">
        <div class="container">
            <div class="row">
                <nav>
                    <div class="nav nav-tabs" id="userTabs" role="tablist">
                        <c:forEach var = "u" items = "${userList}" varStatus="userLoop">
                            <a class="nav-item nav-link text-center" id="userTab-${u.id}" data-toggle="tab" href="#userContent-${u.id}" role="tab" aria-controls="userContent-${u.id}" aria-selected="false">
                                <img class ="img-fluid img-thumbnail rounded-circle" height="100" width="100" alt="NoPic" src='<c:url value="/pictures/${u.fileName}"/>'/>
                                <div class="text-dark">
                                    <p class="font-weight-bold">${u.firstname} ${u.lastname}</p>
                                    <p>${u.countryname}</p>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </nav>

            </div>
            <div class="row">
                <div class="tab-content w-100" id="userContents">
                    <c:forEach var = "u" items = "${userList}" varStatus="userLoop">
                        <div class="tab-pane fade" id="userContent-${u.id}" role="tabpanel" aria-labelledby="userTab-${u.id}">
                            <div class="modal-header">
                                <div class="modal-title w-100 text-center" id="delModalLabel"><h5>"${u.firstname} ${u.lastname}" Data</h5></div>
                                <button type="button" class="close" onclick="closeContent('${u.id}')">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="tab-content">
                                <table class="table table-bordered table-striped text-center" align="center">
                                    <tr>
                                        <th>Firstname</th>
                                        <th>Lastname</th>
                                        <th>Country</th>
                                        <th>Birthdate</th>
                                        <th>Role</th>
                                        <th>Marital Status</th>
                                        <th>Skills</th>
                                        <th></th>
                                    </tr>
                                    <tr>
                                        <td>${u.firstname}</td>
                                        <td>${u.lastname}</td>
                                        <td>${u.countryname}</td>
                                        <td><fmt:formatDate value="${u.birthdate}" type="date" pattern="dd-MM-yyyy"/></td>
                                        <td>${u.role.name}</td>
                                        <td>${u.maritalStatus.name}</td>
                                        <td><c:forEach var="s" items="${u.skills}" varStatus="skillLoop">
                                            ${s.name}
                                            <c:if test="${!skillLoop.last}">,</c:if>
                                        </c:forEach></td>
                                        <td>
                                            <div>
                                                <a  href="<c:url value='/edit-user-${u.id}' />" class="btn btn-primary table-button" type="submit">Edit</a>
                                            </div>
                                            <div>
                                                <a id="delete-${u.id}" deleteLink="<c:url value='/delete-user-${u.id}' />" class="btn btn-danger table-button" type="submit" onclick="openModal( ${u.id}, '${u.firstname}' , '${u.lastname}')" >Delete</a>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%--        <div class="row">--%>
<%--            <div class="center-block text-center w-100">--%>
<%--                <a  href="<c:url value='/newuser' />" class="btn btn-secondary btn-lg w-75" type="submit">Add User</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <form:form method="POST" class="form-horizontal">--%>

<%--            <div class="row form-group justify-content-center mt-4">--%>
<%--                <div class="container">--%>
<%--                    <table id="tableFilter" class="table table-borderless border-0" align="center">--%>
<%--                        <tr>--%>
<%--                            <th>--%>
<%--                                Filter Type--%>
<%--                            </th>--%>
<%--                            <th>--%>
<%--                                Filter Search--%>
<%--                            </th>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td>--%>
<%--                                <select id="filterType0" class="form-control" name="filterType" title="Choose one filter">--%>
<%--                                    <option selected value = "">No filter</option>--%>
<%--                                    <option value = "firstname">Firstname</option>--%>
<%--                                    <option value = "lastname">Lastname</option>--%>
<%--                                    <option value = "countryname">Country</option>--%>
<%--        &lt;%&ndash;                            <option value = "birthdate">Birthdate</option>&ndash;%&gt;--%>
<%--        &lt;%&ndash;                            <option value = "role">Role</option>&ndash;%&gt;--%>
<%--        &lt;%&ndash;                            <option value = "maritalStatus">Marital Status</option>&ndash;%&gt;--%>
<%--        &lt;%&ndash;                            <option value = "skill">Skill</option>&ndash;%&gt;--%>
<%--                                </select>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <div class="input-group">--%>
<%--                                    <input class="form-control" id="filterText0" type="search" size="40" placeholder="Search.." maxlength="30" name="filterText"/>--%>
<%--                                    <div class="input-group-append">--%>
<%--                                        <button type="submit" class="btn btn-info"><i class="fa fa-search"></i></button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <c:if test="${!(filterType == null || filterType == '')}">--%>
<%--                            <tr>--%>
<%--                                <td align="right">--%>
<%--                                    Filter by <p style="display: inline; text-transform: capitalize"><b>${filterType}</b></p>: "${filterText}"--%>
<%--                                </td>--%>
<%--                                <td>--%>
<%--                                    <a  href="Home" class="btn btn-secondary table-button" type="submit">Remove Filter</a>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:if>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form:form>--%>


<%--        <div class="row justify-content-center">--%>
<%--            <div class="container">--%>
<%--                <table class="table table-bordered table-striped text-center" align="center">--%>
<%--                    <tr>--%>
<%--                        <th>Profile Pic</th>--%>
<%--                        <th>Firstname</th>--%>
<%--                        <th>Lastname</th>--%>
<%--                        <th>Country</th>--%>
<%--                        <th>Birthdate</th>--%>
<%--                        <th>Role</th>--%>
<%--                        <th>Marital Status</th>--%>
<%--                        <th>Skills</th>--%>
<%--                        <th></th>--%>
<%--                    </tr>--%>

<%--                    <c:forEach var = "u" items = "${userList}">--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <img class ="img-fluid img-thumbnail rounded" height="100" width="100" alt="NoPic" src='<c:url value="/pictures/${u.fileName}"/>'/>--%>
<%--                        </td>--%>
<%--                        <td>${u.firstname}</td>--%>
<%--                        <td>${u.lastname}</td>--%>
<%--                        <td>${u.countryname}</td>--%>
<%--                        <td><fmt:formatDate value="${u.birthdate}" type="date" pattern="dd-MM-yyyy"/></td>--%>
<%--                        <td>${u.role.name}</td>--%>
<%--                        <td>${u.maritalStatus.name}</td>--%>
<%--                        <td><c:forEach var="s" items="${u.skills}" varStatus="skillLoop">--%>
<%--                            ${s.name}--%>
<%--                            <c:if test="${!skillLoop.last}">,</c:if>--%>
<%--                        </c:forEach></td>--%>
<%--                        <td>--%>
<%--                            <div>--%>
<%--                                <a  href="<c:url value='/edit-user-${u.id}' />" class="btn btn-primary table-button" type="submit">Edit</a>--%>
<%--                            </div>--%>
<%--                            <div>--%>
<%--                                <a id="delete-${u.id}" deleteLink="<c:url value='/delete-user-${u.id}' />" class="btn btn-danger table-button" type="submit" onclick="openModal( ${u.id}, '${u.firstname}' , '${u.lastname}')" >Delete</a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    </c:forEach>--%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="modal" id="modalDelete">--%>
<%--            <div class="modal-dialog" role="document">--%>
<%--                <div class="modal-content">--%>
<%--                    <div class="modal-header">--%>
<%--                        <h5 class="modal-title" id="delModalLabel">Confirm Delete</h5>--%>
<%--                        <button type="button" class="close" onclick="closeModal()">--%>
<%--                            <span aria-hidden="true">&times;</span>--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                    <div class="modal-body">--%>
<%--                        <div id="modalText">--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="modal-footer">--%>
<%--                        <div class="d-inline">--%>
<%--                            <button type="button" class="btn btn-secondary" onclick="closeModal()">No</button>--%>
<%--                        </div>--%>
<%--                        <div class="d-inline">--%>
<%--                            <a id="linkDelete" href="" class="btn btn-primary" type="submit">Yes</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<script>
    // Get the modal
    var modal = document.getElementById('modalDelete');

    function closeContent(id){
        var actives = document.getElementsByClassName("active");
        for (var i = 0; i < actives.length ; i++){
            var elemId = actives[i].getAttribute("id");
            if ( elemId != ('userTab-'+id) || elemId != ('userContent-'+id) )  {
                actives[i].classList.toggle("active");
                actives[i].classList.toggle("show");
            }
        }
    }

    function closeModal(){
        modal.style.display = "none";
    }
    function openModal(userId, name, lastname){
        document.getElementById("modalText").textContent = "Are you sure you want to delete " + name + " " + lastname + "?";
        var deleteLink = document.getElementById("delete-"+userId).getAttribute("deleteLink");
        document.getElementById("linkDelete").setAttribute("href", deleteLink);
        modal.style.display = "block";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

