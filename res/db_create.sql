CREATE TABLE classes_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  name varchar(20) NOT NULL DEFAULT '',
  CONSTRAINT classes_master_pk PRIMARY KEY (id)
);

CREATE TABLE students_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  student_num varchar(11) NOT NULL DEFAULT '',
  last_name varchar(30) NOT NULL DEFAULT '',
  first_name varchar(30) NOT NULL DEFAULT '',
  textbook_num varchar(15) NOT NULL DEFAULT '',
  active boolean NOT NULL DEFAULT TRUE,
  class_id int NOT NULL,
  CONSTRAINT student_class_fk FOREIGN KEY (class_id) REFERENCES classes_master ON DELETE CASCADE,
  CONSTRAINT students_master_pk PRIMARY KEY (id)
);

CREATE TABLE days_master (
  id date NOT NULL DEFAULT CURRENT DATE,
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date date NOT NULL,
  type smallint NOT NULL DEFAULT 0,
  CONSTRAINT days_master_pk PRIMARY KEY (id)
);

CREATE TABLE attendance_log (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  day_id date NOT NULL,
  CONSTRAINT attendance_day_fk FOREIGN KEY (day_id) REFERENCES days_master ON DELETE CASCADE,
  student_id int NOT NULL,
  CONSTRAINT attendance_student_fk FOREIGN KEY (student_id) REFERENCES students_master ON DELETE CASCADE,
  status smallint NOT NULL DEFAULT 0,
  CONSTRAINT attendance_log_pk PRIMARY KEY (id)
);

CREATE TABLE evals_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  name int NOT NULL,
  day_id date NOT NULL,
  CONSTRAINT eval_day_fk FOREIGN KEY (day_id) REFERENCES days_master ON DELETE CASCADE,
  CONSTRAINT evals_master_pk PRIMARY KEY (id)
);

CREATE TABLE evals_log (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  eval_id int NOT NULL,
  CONSTRAINT eval_log_eval_fk FOREIGN KEY (eval_id) REFERENCES evals_master ON DELETE CASCADE,
  student_id int NOT NULL,
  CONSTRAINT eval_log_student_fk FOREIGN KEY (student_id) REFERENCES students_master ON DELETE CASCADE,
  status smallint NOT NULL DEFAULT 0,
  CONSTRAINT evals_log_pk PRIMARY KEY (id)
);

CREATE TABLE homework_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  day_id date NOT NULL,
  CONSTRAINT homework_day_fk FOREIGN KEY (day_id) REFERENCES days_master ON DELETE CASCADE,
  description int NOT NULL,
  out_of smallint NOT NULL DEFAULT 0,
  CONSTRAINT homework_master_pk PRIMARY KEY (id)
);

CREATE TABLE homework_log (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  homework_id int NOT NULL,
  CONSTRAINT homework_log_homework_fk FOREIGN KEY (homework_id) REFERENCES homework_master ON DELETE CASCADE,
  student_id int NOT NULL,
  CONSTRAINT homework_log_student_fk FOREIGN KEY (student_id) REFERENCES students_master ON DELETE CASCADE,
  mark smallint NOT NULL DEFAULT 0,
  CONSTRAINT homework_log_pk PRIMARY KEY (id)
);

CREATE TABLE organization_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  day_id date NOT NULL,
  CONSTRAINT organization_day_fk FOREIGN KEY (day_id) REFERENCES days_master ON DELETE CASCADE,
  dates_recorded_score_max smallint NOT NULL DEFAULT 0,
  notes_in_order_score_max smallint NOT NULL DEFAULT 0,
  notes_secured_in_binder_score_max smallint NOT NULL DEFAULT 0,
  CONSTRAINT organization_master_pk PRIMARY KEY (id)
);

CREATE TABLE organization_log (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  organization_id int NOT NULL,
  CONSTRAINT org_log_org_fk FOREIGN KEY (organization_id) REFERENCES organization_master ON DELETE CASCADE,
  student_id int NOT NULL,
  CONSTRAINT org_log_student_fk FOREIGN KEY (student_id) REFERENCES students_master ON DELETE CASCADE,
  dates_recorded_score smallint NOT NULL DEFAULT 0,
  notes_in_order_score smallint NOT NULL DEFAULT 0,
  notes_secured_in_binder_score smallint NOT NULL DEFAULT 0,
  CONSTRAINT organization_log_pk PRIMARY KEY (id)
);

CREATE TABLE other_master (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  day_id date NOT NULL,
  CONSTRAINT other_day_fk FOREIGN KEY (day_id) REFERENCES days_master ON DELETE CASCADE,
  description varchar(140) NOT NULL DEFAULT '',
  CONSTRAINT other_master_pk PRIMARY KEY (id)
);

CREATE TABLE other_log (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  time_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  other_id int NOT NULL,
  CONSTRAINT other_log_other_fk FOREIGN KEY (other_id) REFERENCES other_master ON DELETE CASCADE,
  student_id int NOT NULL,
  CONSTRAINT other_log_student_fk FOREIGN KEY (student_id) REFERENCES students_master,
  complete boolean NOT NULL DEFAULT FALSE,
  CONSTRAINT other_log_pk PRIMARY KEY (id)
);