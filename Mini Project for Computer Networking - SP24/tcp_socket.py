import socket

def init_tcp_socket()->socket.socket:
    #raise NotImplemented("Please implement this function") 
    s = socket.socket (socket.AF_INET, socket.SOCK_STREAM)
    return s

def connect_to_remote(s:socket.socket,addr:str,port:int)->bool:
    #raise NotImplemented("Please implement this function") 
    s.connect((addr, port))
    return True

def send_lastname_dot_number(s:socket.socket,lastname_dot_number:str):
    #raise NotImplemented("Please implement this function")
    s.send(lastname_dot_number.encode("utf-8"))

def get_uuid(s:socket.socket)->str:
    #raise NotImplemented("Please implement this function") 
    received = s.recv(4096)
    return received
    
def save_uuid(uuid:str):
    #raise NotImplemented("Please implement this function") 
    print("\nReceived UUID:", uuid.decode("utf-8"))
    
if __name__=="__main__":
    s = init_tcp_socket()
    connect_to_remote(s,"127.0.0.1",12345)
    send_lastname_dot_number(s,"espanioly.1")
    uuid = get_uuid(s)
    save_uuid(uuid)
    #print("\nReceived UUID:", uuid.decode("utf-8"))
    s.close()
    
