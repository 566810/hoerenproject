package blend.buddyapp.api.resources.login.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-18T19:21:00.123+02:00[Europe/Prague]")
public class LoginRequestBody   {
  @JsonProperty("student_number")
  private String studentNumber;
  @JsonProperty("password")
  private String password;
  public LoginRequestBody studentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
    return this;
  }
  @ApiModelProperty(example = "566810", required = true, value = "")
  @NotNull
  public String getStudentNumber() {
    return studentNumber;
  }
  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }
  public LoginRequestBody password(String password) {
    this.password = password;
    return this;
  }
  @ApiModelProperty(example = "!4cGb95656b", required = true, value = "")
  @NotNull

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }






  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginRequestBody loginRequestBody = (LoginRequestBody) o;
    return Objects.equals(this.studentNumber, loginRequestBody.studentNumber) &&
        Objects.equals(this.password, loginRequestBody.password);
  }


  @Override
  public int hashCode() {
    return Objects.hash(studentNumber, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginRequestBody {\n");
    sb.append("    studentNumber: ").append(toIndentedString(studentNumber)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

