# Running app

## Option 1:
`docker compose up`

## Option 2:
`docker run --name postgres -e POSTGRES_PASSWORD=postPassword -p 5432:5432 -d postgres`
&
`mvnw spring-boot:run`

-----------------------------------------------------------------------------------------------------


# App Details

## Defaults
1. Server port: 8080
2. Active profile: dev
3. DB name: postgres
4. DB user: postgres
5. DB password: postPassword


## API doc

1. GET "/api/item/create-space"
    - RequestParam: name="spaceName", type=String, required=false

2. GET "/api/item/create-folder"
    - RequestParam: name="spaceName", type=String, required=false
    - RequestParam: name="folderName", type=String, required=false

3. POST "/api/item/create-file"
    - RequestParam: name="folderName", type=String, required=false
    - RequestParam: name="fileName", type=String, required=false
    - RequestParam: name="file", type=MultipartFile, required=false

4. GET "/api/item/view-file"
    - RequestParam: name="fileName", type=String, required=false

5. GET "/api/file/download"
    - RequestParam: name="fileName", type=String, required=false