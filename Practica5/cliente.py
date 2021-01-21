import socket
import threading
import sys
import pickle
import gui
from PyQt5 import QtCore, QtGui, QtWidgets
class Cliente():
	"""docstring for Cliente"""
	def __init__(self, host="localhost", port=4000,name=""):
		
		self.name=name
		self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.sock.connect((str(host), int(port)))

		#interface initialice
		app = QtWidgets.QApplication(sys.argv)
		Form = QtWidgets.QWidget()	 
		self.ui = gui.Ui_Form()
		self.ui.setupUi(Form)
			
		

		msg_recv = threading.Thread(target=self.msg_recv)

		msg_recv.daemon = True
		msg_recv.start()

		#config interfaz
		   
		self.ui.em1.setText('\U0001F600')
		self.ui.em1.clicked.connect(lambda:self.emoji(self.ui.em1.text()))
		self.ui.em2.setText('\U0001F633')
		self.ui.em2.clicked.connect(lambda:self.emoji(self.ui.em2.text()))
		self.ui.pushButton.clicked.connect(self.push_env)
		self.ui.em3.setText('\U0001F604')
		self.ui.em3.clicked.connect(lambda:self.emoji(self.ui.em3.text()))
		self.ui.em4.setText('\U0001F601')
		self.ui.em4.clicked.connect(lambda:self.emoji(self.ui.em4.text()))
		self.ui.em5.setText('\U0001F606')
		self.ui.em5.clicked.connect(lambda:self.emoji(self.ui.em5.text()))
		self.ui.em6.setText('\U0001F605')
		self.ui.em6.clicked.connect(lambda:self.emoji(self.ui.em6.text()))
		self.ui.em7.setText('\U0001F602')
		self.ui.em7.clicked.connect(lambda:self.emoji(self.ui.em7.text()))
		self.ui.em8.setText('\U0001F642')
		self.ui.em8.clicked.connect(lambda:self.emoji(self.ui.em8.text()))
		self.ui.em9.setText('\U0001F643')
		self.ui.em9.clicked.connect(lambda:self.emoji(self.ui.em9.text()))
		self.ui.em10.setText('\U0001F970')
		self.ui.em10.clicked.connect(lambda:self.emoji(self.ui.em10.text()))
		Form.show()
		sys.exit(app.exec_())

		
		
			
	def emoji(self,emo): 
		self.ui.textEdit_2.insertPlainText(emo)


	def msg_recv(self):
		while True:
			try:
				data = self.sock.recv(1024)
				if data:
					self.ui.label_2.append(pickle.loads(data))
					print(pickle.loads(data))
			except:
				pass

	def push_env(self):
		msg = self.ui.textEdit_2.toPlainText()
		self.name=self.ui.textEdit.toPlainText()
		self.send_msg(msg)
		self.ui.label_2.append(msg)

		self.ui.textEdit_2.clear()



	def send_msg(self, msg):
		if msg== 'salir':
			self.sock.close()
			sys.exit()
		self.sock.send(pickle.dumps(self.name+": "+msg))


name = ""
c = Cliente(name=name)