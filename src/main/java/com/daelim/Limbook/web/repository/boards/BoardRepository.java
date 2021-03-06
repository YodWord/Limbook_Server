package com.daelim.Limbook.web.repository.boards;

import com.daelim.Limbook.domain.Board;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.BoardDTO.CreateBoardDTO;
import com.daelim.Limbook.web.controller.dto.BoardDTO.UpdateBoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    public Board saveBoard(CreateBoardDTO createBoardDTO, User user) throws Exception;

    public Optional<Board> findById(Integer id) throws Exception;

    public List<Board> findAll() throws Exception;

    public Board updateBoard(UpdateBoardDTO updateBoardDTO, Integer boardId, User user) throws Exception;

    public Board deleteBoard(Integer boardId, User user) throws Exception;

}
