<?php 
	/*
	*
	*
	*/
	class Usuario{
		private $USERNAME_CL;
		private $CONTRASENA_CL;
        private $EMAIL_CL;
        private $NOMBRE_CL;
        private $AP_CL;
        private $AM_CL;
        private $TEL_CL;
        private $MUN_CL;
        #############################
        public function getNombre(){
			return $this->USERNAME_CL;
		}
		public function setNombre($USERNAME_CL){
			$this->USERNAME_CL = $USERNAME_CL;
		}
        #############################
        public function getEmail(){
			return $this->EMAIL_CL;
		}
		public function setEmail($EMAIL_CL){
			$this->EMAIL_CL = $EMAIL_CL;
		}
        #############################
        public function getClave(){
			return $this->CONTRASENA_CL;
		}
		public function setClave($CONTRASENA_CL){
			$this->CONTRASENA_CL = $CONTRASENA_CL;
		}
        #############################
        public function getNombrecl(){
			return $this->NOMBRE_CL;
		}
        public function setNombrecl($NOMBRE_CL){
			$this->NOMBRE_CL = $NOMBRE_CL;
		}
        #############################        
		public function getAp(){
			return $this->AP_CL;
		}
        public function setAp($AP_CL){
			$this->AP_CL = $AP_CL;
		}
        #############################
        public function getAm(){
			return $this->AM_CL;
		}
		public function setAm($AM_CL){
			$this->AM_CL = $AM_CL;
		}
        #############################
        public function getTel(){
			return $this->TEL_CL;
		}
		public function setTel($TEL_CL){
			$this->TEL_CL = $TEL_CL;
		}
        #############################
        public function getMun(){
			return $this->MUN_CL;
		}
		public function setMun($MUN_CL){
			$this->MUN_CL = $MUN_CL;
		}		
	}
?>