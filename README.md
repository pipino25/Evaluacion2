Este proyecto es la aplicación de los requerimientos presentados en el caso a el proyecto desarrollado para la evaluación número 2, integrando fucnionalidades de firebase y mqtt.

Funcionalidades Firebase:

  Logrado al incluir las dependencias en el proyecto ![image](https://github.com/user-attachments/assets/f5361a7f-aad8-4b9d-ba2a-70d6074e77b9)

  Además de la importación de librerías para su uso en cada clase que lo requiera

  ![image](https://github.com/user-attachments/assets/ee8ea597-9ace-4191-9e0f-9a19fe507be9)

  Al comienzo de las activitys que lo requieran, se debe llamar a la instancia de la base de datos externa y se debe indicar la referencia para las consultas (la "tabla" de destino)
  
  ![image](https://github.com/user-attachments/assets/ef71d140-6648-4daf-9808-5d9a070b2255)

  Una vez construido una estructura que guarda los datos desde la aplicación para ser enviada al sistema de la base de datos...

  ![image](https://github.com/user-attachments/assets/27497258-b1a8-4c3b-a804-a0b942dc7e1c)

  Se usa las funciones de firebase para realizar las acciones CRUD correspondientes:

  Create
  
  ![image](https://github.com/user-attachments/assets/5adbcdc1-9b20-49c1-bf01-27f94e50f60b)

  Read

  ![image](https://github.com/user-attachments/assets/98d46f80-32d0-4d85-8501-04ee4d1188d7)

  Update

  ![image](https://github.com/user-attachments/assets/2447ce01-a9c2-4465-aeff-fa35323fd081)

  Delete

  ![image](https://github.com/user-attachments/assets/687b35a0-a614-40e0-b345-eecf4d213608)

  Demostración

  Create
  
  ![image](https://github.com/user-attachments/assets/1700f8fa-cb82-4461-a5c6-4a411be9cc26)

  ![image](https://github.com/user-attachments/assets/1affb7e9-52f4-4ef4-9f54-0b4fc46acec4)


  Read

  ![image](https://github.com/user-attachments/assets/832a812a-a713-4a90-8cee-8fca8b50d117)

  
  Update

  ![image](https://github.com/user-attachments/assets/935172c0-e95b-4c0e-9cf7-6a5dfcaf2555)

  ![image](https://github.com/user-attachments/assets/c9120cd4-2a2c-4e06-b655-427e54e25ed1)

  ![image](https://github.com/user-attachments/assets/f7d72886-57c9-41ad-b0f7-c736c7c4f126)



  
  Delete

  ![image](https://github.com/user-attachments/assets/082e9905-120e-4553-af24-a959a8552300)

  ![image](https://github.com/user-attachments/assets/80e69feb-599b-4101-9026-f742592a89c3)

  Ahora hay un dato menos en la database
  ![image](https://github.com/user-attachments/assets/1834860c-847c-4918-82e2-f28349aa25d1)


Funcionalidades mqtt

  Se usó el protocolo mqtt para crear un registro de los cambios hechos en la base de datos

  Dependencias y librerias establecidas y usadas para la clase Mqtt andler, que alberga funciones para el manejo de el protocolo

  ![image](https://github.com/user-attachments/assets/7c9a60c8-ca57-47a5-8142-ae48c96b05fa)

  ![image](https://github.com/user-attachments/assets/87afa9c3-5dad-4a95-8a23-6670e249d532)

  ![image](https://github.com/user-attachments/assets/a38174a2-9c07-40a0-b3f2-7abc08dccebb)

  Se llama a la clase y se crean los datos necesarios para la conexión en cada activity que la vaya a ocupar

  ![image](https://github.com/user-attachments/assets/d5715bd5-4d4c-4efd-9796-36030affa75e)

  ![image](https://github.com/user-attachments/assets/2ae53b5a-a1cf-4dab-94ba-4bf81883bde8)

  Se llama ala función para mandar un mensaje por el protocolo

  ![image](https://github.com/user-attachments/assets/835f8cc6-a5db-4390-ba0b-08e0323a4286)

  Se establecieron mensajes para cada acción con la base de datos

  ![image](https://github.com/user-attachments/assets/13b0ef11-6b2f-416e-9272-222892b80b5a)

  ![image](https://github.com/user-attachments/assets/6eeb18e6-b5dd-4e50-9996-3d911cc868d2)

  A través de una app de celular, se establece la comunicación por el protocolo y se accede a los registros del sistema

  ![Screenshot_20241204-021855](https://github.com/user-attachments/assets/db509cd6-fec9-40e5-8abc-8b939366c3e9)

Funcionalidad de credenciales de ingreso

  Esta función se realizó de manera simplificada y local, solo para demostrar la posibilidad del sistema de lograrlo 
  Las credenciales son "sistemas" y "1234"
![image](https://github.com/user-attachments/assets/5b80222d-83dc-43b1-9a60-b2589aa7637e)

Demostración

![image](https://github.com/user-attachments/assets/80eb0b26-bf39-4a7a-bf8f-11ef9d49ec23)

![image](https://github.com/user-attachments/assets/f5dcbf4d-670c-4629-ad8f-f45c959c396b)

  
![image](https://github.com/user-attachments/assets/2cfc49e4-dc4e-4425-a654-b5b60b641652)







  




