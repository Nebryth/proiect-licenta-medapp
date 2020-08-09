export default {
  // sadmin: {
  //   defaultUrl: "",
  //   prefix: "/sadmin",
  //   items: [
  //     {
  //       src: "/users",
  //       title: "Staff Users"
  //     },
  //     {
  //       src: "/customers",
  //       title: "Doctor's Offices"
  //     }
  //   ]
  // },
  2 : { //ADMIN
    defaultUrl: "/admin/users",
    prefix: "/admin",
    items: [
      {
        src: "/staff",
        title: "Users"
      },
      {
        src: "/customer",
        title: "Doctor's Office"
      }
    ]
  },
  3 : { //DOCTOR
    defaultUrl: "/app/appointments",
    prefix: "/app",
    items: [
      {
        src: "/patients",
        title: "Patients"
      },
      {
        src: "/diagnoses",
        title: "Diagnoses"
      },
      {
        src: "/appointments",
        title: "Appointments"
      }
    ]
  },
  4: {  //NURSE
    defaultUrl: "/app/patients",
    prefix: "/app",
    items: [
      {
        src: "/appointments",
        title: "Appointments"
      },
      {
        src: "/patients",
        title: "Patients"
      }
    ]
  }
};
