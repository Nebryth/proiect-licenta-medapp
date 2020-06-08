CREATE DEFINER=`user123`@`%` TRIGGER `patientsdetails_BEFORE_INSERT` BEFORE INSERT ON `patientsdetails` FOR EACH ROW BEGIN
	set @calculated1 = new.current_weight;
    set @HeightInCm = new.current_height / 100;
    
    set @calculated2 = @HeightInCm * @HeightInCm;
	set @calculated = @calculated1 / @calculated2;
    
    set new.bmi = truncate(@calculated, 1);
END