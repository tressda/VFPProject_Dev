package com.vfp.tres;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import tres.common.DbConstant;
import tres.common.JSFBoundleProvider;
import tres.common.JSFMessagers;
import tres.common.SessionUtils;
import tres.dao.impl.BoardImpl;
import tres.dao.impl.ContactImpl;
import tres.dao.impl.InstitutionImpl;
import tres.dao.impl.UserImpl;
import tres.domain.Board;
import tres.domain.Contact;
import tres.domain.Institution;
import tres.domain.Users;
import tres.vfp.dto.BoardDto;
import tres.vfp.dto.ContactDto;
import tres.vfp.dto.UserDto;

@ManagedBean
@ViewScoped
public class BoardController implements Serializable, DbConstant {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private String CLASSNAME = "UserContactController :: ";
	private static final long serialVersionUID = 1L;
	/* to manage validation messages */
	private boolean isValid;
	/* end manage validation messages */
	private Institution institution;
	private Board board;
	private Users users;
	private BoardDto boardDto;
	private UserDto userDto;
	private Users usersSession;
	private List<Board> boardList = new ArrayList<Board>();
	private List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
	private List<Institution> institutionList = new ArrayList<Institution>();
	private List<Board>ListBoard= new ArrayList<Board>();
	private List<BoardDto>mainBoard= new ArrayList<BoardDto>();
	private int instituteNumber;
	private int boardNumber;
	private String boardName;
	/* class injection */
	JSFBoundleProvider provider = new JSFBoundleProvider();
	UserImpl usersImpl = new UserImpl();
	ContactImpl contactImpl = new ContactImpl();
	BoardImpl boardImpl = new BoardImpl();
	InstitutionImpl instituteImpl = new InstitutionImpl();
	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	private boolean rendered = true;
	private boolean renderForm;
	private String range;
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		usersSession = (Users) session.getAttribute("userSession");
		if (users == null) {
			users = new Users();
		}
		if (boardDto == null) {
			boardDto = new BoardDto();
		}
		if (board == null) {
			board = new Board();
		}

		try {
			LOGGER.info("initialise lists:: ");
			/*
			  boardList = boardImpl.getGenericListWithHQLParameter(new String[] {
			  "genericStatus" }, new Object[] { ACTIVE }, "Board", "boardId desc");
			 */
			
			boardList = boardImpl.getListWithHQL("from Board", 0, endCateRecord);

			institutionList = instituteImpl.getGenericListWithHQLParameter(new String[] { "genericStatus" },
					new Object[] { ACTIVE }, "Institution", "institutionId desc");
			LOGGER.info("lis size :: " + institutionList.size());

			boardDtoList = allBoard(boardList);
			
			for(Object[] data:boardImpl.reportList("select b.boardId,b.boardName from Board b where b.boardId in(select d.board from Board d)")) {
				BoardDto boardDto = new BoardDto();
				boardDto.setEditable(false);
				boardDto.setBoardId(Integer.parseInt(data[0]+""));
				boardDto.setBoardName(data[1]+"");
				mainBoard.add(boardDto);	
			}
		} catch (Exception e) {
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void showBoard() throws Exception {
		if (range.equals("5") || (range.equals("10")) || (range.equals("15"))) {
			int endRecords = Integer.parseInt(range);
			boardList = boardImpl.getListWithHQL("from Board", 0, endRecords);
			boardDtoList = allBoard(boardList);
			
		} else {
			boardList = boardImpl.getGenericListWithHQLParameter(new String[] {
			  "genericStatus" }, new Object[] { ACTIVE }, "Board", "boardId desc");
			boardDtoList = allBoard(boardList);
		}		
	}

	
	public String showParentBoard(BoardDto bd) {
		if(null!=bd.getBoard()) {
			return bd.getBoard().getBoardName();
		}else {
			return bd.getBoardName();
		}
	}
	public List<BoardDto> allBoard(List<Board> boardlist) {

		boardDtoList = new ArrayList<BoardDto>();
		for (Board board : boardlist) {
			BoardDto boardDto = new BoardDto();
			boardDto.setEditable(false);
			boardDto.setBoardId(board.getBoardId());
			boardDto.setDescription(board.getDescription());
			/*if (board.getBoard() != null) {
				boardDto.setBoardName(board.getBoardName());
			} else {
				
				boardDto.setBoardName(board.getBoardName());
				LOGGER.info("Parent Board name:::::::"+board.getBoardName());
				
				
				//boardDto.setBoardName("This is the main board");
			}*/
			boardDto.setBoardName(board.getBoardName());
			boardDto.setInstitution(board.getInstitution());
			boardDto.setBoard(board.getBoard());
			boardDto.setStatus(board.getStatus());
			if (board.getStatus().equals(ACTIVE)) {
				boardDto.setAction(DESACTIVE);
			} else {
				boardDto.setAction(ACTIVE);
			}
			boardDtoList.add(boardDto);
			// boardList.add(boardDto);
		}
		return boardDtoList;

	}

	public String newAction(BoardDto boards) throws Exception {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		Board bd = new Board();
		bd = new Board();
		if (boards != null)
			LOGGER.info("here we are sart for " + boards.getBoardId() + "");
			
			/* us = usersImpl.gettUserById(user.getUserId(), "userId"); */
			bd = boardImpl.getBoardById(boards.getBoardId(), "boardId");
		if (bd != null)
			LOGGER.info("here update sart for " + bd + " useriD " + bd.getGenericStatus());
		boards.setEditable(false);
		bd.setBoardName(boards.getBoardName());
		bd.setDescription(boards.getDescription());
		/*board=boardImpl.getBoardById(instituteNumber, "boardId");*/
		bd.setBoard(boards.getBoard());
		boardImpl.UpdateBoard(bd);
		boardList = boardImpl.getListWithHQL("from Board", 0, endrecord);
		boardDtoList = allBoard(boardList);
		JSFMessagers.resetMessages();
		setValid(true);
		JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
		return null;

	}

	public String editAction(BoardDto board) {

		board.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String cancel(BoardDto board) {
		board.setEditable(false);

		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String updateStatus(BoardDto board) throws Exception {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		/* Users us = new Users(); */
		Board bd = new Board();
		bd = new Board();

		if (board != null)
			bd = boardImpl.getBoardById(board.getBoardId(), "boardId");
			LOGGER.info("here update sart for " + bd + " useriD " + bd.getGenericStatus());
		if (board.getStatus().equals(ACTIVE)) {
			bd.setStatus(DESACTIVE);
		} else {
			bd.setStatus(ACTIVE);
		
		}
		boardImpl.UpdateBoard(bd);

		JSFMessagers.resetMessages();
		setValid(true);
		JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.userupdate"));
		boardList = boardImpl.getListWithHQL("from Board", 0, endrecord);
		boardDtoList = allBoard(boardList);
		return "null";
	}

	public String saveBoard() {
		try {
			Institution inst = new Institution();
			Board bd1 = new Board();
			try {

				Board bd = new Board();
				bd = boardImpl.getModelWithMyHQL(new String[] { "boardName" }, new Object[] { boardName },
						"from Board");

				inst = instituteImpl.getModelWithMyHQL(new String[] { "genericstatus", "institutionType", "createdBy" },
						new Object[] { ACTIVE, "HeadQuoter", usersSession.getViewId() }, "from Institution");
				LOGGER.info(inst.getInstitutionId() + ":::------->>>>>>Institute founded");

				bd1 = boardImpl.getModelWithMyHQL(new String[] { "boardId", "genericstatus" },
						new Object[] { boardNumber, ACTIVE }, "from Board ");

				if (null != bd) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.board"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: Board already  recorded in the system! ");
					return null;
				}

			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}

			board.setCreatedBy(usersSession.getViewId());
			board.setCrtdDtTime(timestamp);
			board.setGenericStatus(ACTIVE);
			board.setStatus(ACTIVE);
			board.setUpDtTime(timestamp);

			if (null != bd1 || null == bd1) {
				if (null != inst) {

					board.setInstitution(instituteImpl.getInstitutionById(inst.getInstitutionId(), "institutionId"));
					board.setUpdatedBy(usersSession.getViewId());
					board.setBoardName(boardName);
					board.setBoard(bd1);
					boardImpl.saveBoards(board);
					JSFMessagers.resetMessages();
					setValid(true);
					JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.board"));
					LOGGER.info(CLASSNAME + ":::board Details is saved");
					clearContactFuileds();
				}
			}
			return null;
		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public String saveBoardFromModal() throws Exception {
		try {

			try {

				Board bd = new Board();
				bd = boardImpl.getModelWithMyHQL(new String[] { "boardName" }, new Object[] { boardName },
						"from Board");
				if (null != bd) {

					JSFMessagers.resetMessages();
					setValid(false);
					JSFMessagers.addErrorMessage(getProvider().getValue("error.server.side.dupicate.board"));
					LOGGER.info(CLASSNAME + "sivaserside validation :: Board already  recorded in the system! ");
					return null;
				}

			} catch (Exception e) {
				JSFMessagers.resetMessages();
				setValid(false);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
				LOGGER.info(CLASSNAME + "" + e.getMessage());
				e.printStackTrace();
				return null;
			}

			board.setCreatedBy(usersSession.getViewId());
			board.setCrtdDtTime(timestamp);
			board.setGenericStatus(ACTIVE);
			board.setUpDtTime(timestamp);
			Institution inst = new Institution();
			inst = instituteImpl.getModelWithMyHQL(new String[] { "institutionId", "genericstatus" },
					new Object[] { instituteNumber, ACTIVE }, "from Institution ");

			Board bd1 = new Board();

			bd1 = boardImpl.getModelWithMyHQL(new String[] { "boardId", "genericstatus" },
					new Object[] { boardNumber, ACTIVE }, "from Board ");
			if ((null != inst) || (null != bd1)) {
				LOGGER.info(inst.getInstitutionId() + ":::------->>>>>>Institute founded");
				board.setInstitution(instituteImpl.getInstitutionById(inst.getInstitutionId(), "institutionId"));
				board.setUpdatedBy(usersSession.getViewId());
				board.setBoardName(boardName);
				board.setBoard(bd1);
				boardImpl.saveBoards(board);
				JSFMessagers.resetMessages();
				setValid(true);
				JSFMessagers.addErrorMessage(getProvider().getValue("com.save.form.board"));
				LOGGER.info(CLASSNAME + ":::Contact Details is saved");
				clearContactFuileds();
				return "/menu/boardOrganigram.xhtml?faces-redirect=true";
			}
			return "/menu/Organigram.xhtml?faces-redirect=true";
		} catch (HibernateException e) {
			LOGGER.info(CLASSNAME + ":::Contact Details is fail with HibernateException  error");
			JSFMessagers.resetMessages();
			setValid(false);
			JSFMessagers.addErrorMessage(getProvider().getValue("com.server.side.internal.error"));
			LOGGER.info(CLASSNAME + "" + e.getMessage());
			e.printStackTrace();
			return "";
		}

	}

	public void showBoardForm() {
		renderForm = true;
		rendered = false;
	}

	private void clearContactFuileds() {
		board = new Board();
		// usersDetails=null;
	}

	public String saveAction(UserDto user) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		Users us = new Users();
		us = new Users();
		us = usersImpl.gettUserById(user.getUserId(), "userId");

		LOGGER.info("here update sart for " + us + " useriD " + us.getUserId());

		user.setEditable(false);
		us.setFname(user.getFname());
		us.setLname(user.getLname());

		usersImpl.UpdateUsers(us);

		// return to current page
		return "/menu/ListOfUsers.xhtml?faces-redirect=true";

	}

	public String cancel(UserDto user) {
		user.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editAction(UserDto user) {

		user.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String saveContactAction(ContactDto contact) {
		LOGGER.info("update  saveAction method");
		// get all existing value but set "editable" to false
		Contact cont = new Contact();
		cont = new Contact();
		cont = contactImpl.getContactById(contact.getContactId(), "contactId");

		LOGGER.info("here update sart for " + cont + " useriD " + cont.getContactId());

		contact.setEditable(false);
		cont.setContactDetails(contact.getContactDetails());
		cont.setEmail(contact.getEmail());
		cont.setPhone(contact.getPhone());
		contactImpl.UpdateContact(cont);

		// return to current page
		return "/menu/ViewUsersContacts.xhtml?faces-redirect=true";

	}

	public String cancelContact(ContactDto contact) {
		contact.setEditable(false);
		// usersImpl.UpdateUsers(user);
		return null;

	}

	public String editContactAction(ContactDto contact) {

		contact.setEditable(true);
		// usersImpl.UpdateUsers(user);
		return null;
	}

	public String addNewContact() {

		return "/menu/UserContacts.xhtml?faces-redirect=true";
	}

	public String getCLASSNAME() {
		return CLASSNAME;
	}

	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public JSFBoundleProvider getProvider() {
		return provider;
	}

	public void setProvider(JSFBoundleProvider provider) {
		this.provider = provider;
	}

	public UserImpl getUsersImpl() {
		return usersImpl;
	}

	public void setUsersImpl(UserImpl usersImpl) {
		this.usersImpl = usersImpl;
	}

	public ContactImpl getContactImpl() {
		return contactImpl;
	}

	public void setContactImpl(ContactImpl contactImpl) {
		this.contactImpl = contactImpl;
	}

	public Users getUsersSession() {
		return usersSession;
	}

	public void setUsersSession(Users usersSession) {
		this.usersSession = usersSession;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public BoardDto getBoardDto() {
		return boardDto;
	}

	public void setBoardDto(BoardDto boardDto) {
		this.boardDto = boardDto;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}

	public List<Institution> getInstitutionList() {
		return institutionList;
	}

	public void setInstitutionList(List<Institution> institutionList) {
		this.institutionList = institutionList;
	}

	public BoardImpl getBoardImpl() {
		return boardImpl;
	}

	public void setBoardImpl(BoardImpl boardImpl) {
		this.boardImpl = boardImpl;
	}

	public InstitutionImpl getInstituteImpl() {
		return instituteImpl;
	}

	public void setInstituteImpl(InstitutionImpl instituteImpl) {
		this.instituteImpl = instituteImpl;
	}

	public List<BoardDto> getBoardDtoList() {
		return boardDtoList;
	}

	public void setBoardDtoList(List<BoardDto> boardDtoList) {
		this.boardDtoList = boardDtoList;
	}

	public int getInstituteNumber() {
		return instituteNumber;
	}

	public void setInstituteNumber(int instituteNumber) {
		this.instituteNumber = instituteNumber;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public boolean isRenderForm() {
		return renderForm;
	}

	public void setRenderForm(boolean renderForm) {
		this.renderForm = renderForm;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public List<Board> getListBoard() {
		return ListBoard;
	}

	public void setListBoard(List<Board> listBoard) {
		ListBoard = listBoard;
	}

	public List<BoardDto> getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(List<BoardDto> mainBoard) {
		this.mainBoard = mainBoard;
	}	
}
