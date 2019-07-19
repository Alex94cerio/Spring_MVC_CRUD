<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <form:form method="POST" modelAttribute="user" class="form-horizontal" enctype="multipart/form-data">
        <form:input type="hidden" path="id" id="id"/>
        <div class="row form-group justify-content-center">
                <div class="col">
                    <label class="w-100 text-center font-weight-bold" for="firstname" >First Name *</label>
                    <form:input type="text" size="30" maxlength="30" path="firstname" id="firstname" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="firstname" class="text-danger"/>
                    </div>
                </div>
                <div class="col">
                    <label class="w-100 text-center font-weight-bold" for="lastname">Last Name *</label>
                    <form:input type="text" path="lastname" id="lastname" class="form-control input-sm" />
                    <div class="has-error">
                    <form:errors path="lastname" class="text-danger"/>
                    </div>
                </div>
        </div>
        <div class="row form-group">
            <div class="col">
                <label class="w-100 text-center font-weight-bold" for="countryname" >Country</label>
                <form:input type="text" path="countryname" id="countryname" class="form-control input-sm" />
            </div>
            <div class="col">
            </div>
        </div>
        <div class="row form-group justify-content-center">
            <div class="col">
                <label class="w-100 text-center font-weight-bold" for="role" >Role</label>
                <form:select id="role" path="role.id" items="${roles}" itemValue="id" itemLabel="name" class="form-control input-sm" />
            </div>
            <div class="col">
                <label class="w-100 text-center font-weight-bold" for="maritalStatus" >Marital Status</label>
                <form:select id="maritalStatus" path="maritalStatus.id" items="${maritalStatuses}" itemValue="id" itemLabel="name" class="form-control input-sm" />
            </div>
        </div>
        <div class="row form-group justify-content-center">
            <div class="col">
                <label class="w-100 text-center font-weight-bold" for="birthdate">Birthdate *</label>
                <form:input type="date" path="birthdate" id="birthdate" class="form-control input-sm" />
                <div class="has-error">
                <form:errors path="birthdate" class="text-danger"/>
                </div>

            </div>
            <div class="col">
                <label class="w-100 text-center font-weight-bold" for="skills">Skills</label>
                <div class="text-left">
                    <form:checkboxes
                            items="${skills}"
                            cssClass="m-1"
                            element="span class='d-inline-flex text-nowrap m-2'"
                            id="skills"
                            path="skills"
                            itemLabel="name"
                            itemValue="id"
                    />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <input type="file" name="file" accept="image/png, image/jpeg"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col">
                <a  href="Home" class="btn h-100" type="submit">Back</a>
            </div>
            <div class="col">
                <input class="w-50 h-100" type="SUBMIT" value="${not empty user.id ? 'Save Edits' : 'Create User'}">
            </div>
        </div>
    </form:form>
</div>