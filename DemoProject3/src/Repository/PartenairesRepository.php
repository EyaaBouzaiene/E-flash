<?php

namespace App\Repository;

use App\Entity\Partenaires;
use App\Entity\Partenairesecours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;


/**
 * @method Partenaires|null find($id, $lockMode = null, $lockVersion = null)
 * @method Partenaires|null findOneBy(array $criteria, array $orderBy = null)
 * @method Partenaires[]    findAll()
 * @method Partenaires[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PartenairesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Partenaires::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Partenaires $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Partenaires $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    

   

    // /**
    //  * @return Partenaires[] Returns an array of Partenaires objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Partenaires
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */



    
################################################ nomMarque dont la qualite<3: MAIL ###########################################################################"" 

    public function selectMarque()
    {
     return $this->getEntityManager()
     
         ->createQuery('SELECT p.nommarquep FROM App\Entity\Partenaires p,App\Entity\Stock s 
          WHERE s.partenaire =p.idp
         and s.qualites <3 ')
        
             ->getResult(); 
 
    }
 
#########################################Partenaire Secours #######################################""


      public function selectcategorie()
     {
    
    
         return $this->getEntityManager()
      ->createQuery(
          'SELECT p.categoriep as categorie
          FROM App\Entity\Partenaires p,App\Entity\Stock s
          WHERE s.partenaire =p.idp
          and s.qualites <3'
      )
      
      ->getResult();
 
          
  
      } 


      public function selectMailParcategorie($categorie)
      {
     
     
          return $this->getEntityManager()
       ->createQuery(
           'SELECT ps.mailps as mail
           FROM App\Entity\Partenairesecours ps, App\Entity\Partenaires p
           WHERE ps.categorieps =:categorie'
       )
       ->setParameter('categorie',$categorie)
       
       ->getResult();
  
           
   
       } 

       



################################################ TRI PARTENAIRE DESC PAR DATE  ###########################################################################"" 

   public function TRIEpart()
   {
  
  
    return $this->createQueryBuilder('p')
    ->addOrderBy('p.dateajout', 'desc')
    ->getQuery()
    ->getResult()
;
        

    }

    
    
}
